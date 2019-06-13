package endercrypt.minesweeper;

import java.awt.Image;
import java.util.Objects;

import endercrypt.minesweeper.graphics.MinesweeperGraphicsIndex;
import endercrypt.minesweeper.graphics.MinesweeperGraphicsPointer;

public class MinesweeperTile extends MinesweeperChild
{
	private int x, y;
	private boolean mine;

	private boolean open = false;
	private MinesweeperMark mark = MinesweeperMark.UNMARKED;

	protected MinesweeperNeighbours neighbours;

	public MinesweeperTile(Minesweeper minesweeper, int x, int y, boolean mine)
	{
		super(minesweeper);
		this.x = x;
		this.y = y;
		this.mine = mine;
	}

	protected void initializeTile()
	{
		this.neighbours = new MinesweeperNeighbours(this);
	}

	public int getX()
	{
		return this.x;
	}

	public int getY()
	{
		return this.y;
	}

	public boolean isOpen()
	{
		return this.open;
	}

	public void open()
	{
		if (this.open == true)
		{
			// throw new IllegalStateException(this + " is already open");
			return;
		}
		getMinesweeper().getInformation().validateAlive();
		if (this.mine)
		{
			getMinesweeper().getInformation().state = MinesweeperGameState.DEAD;
		}
		else
		{
			autoOpen();
		}
		getMinesweeper().onModify();
	}

	protected void autoOpen()
	{
		if (this.mine)
		{
			return;
		}
		if (this.open)
		{
			return;
		}
		this.open = true;
		if (this.neighbours.countMines() == 0)
		{
			for (MinesweeperTile tile : neighbours().tiles().list())
			{
				tile.autoOpen();
			}
		}
	}

	protected boolean isMine()
	{
		return this.mine;
	}

	public MinesweeperNeighbours neighbours()
	{
		return this.neighbours;
	}

	public void setMark(MinesweeperMark mark)
	{
		Objects.requireNonNull(mark);
		if (this.mark == mark)
		{
			// throw new IllegalStateException(this + " is already marked with " + mark);
			return;
		}
		this.mark = mark;
		getMinesweeper().onModify();
	}

	public MinesweeperMark getMark()
	{
		return this.mark;
	}

	public boolean isOnBoundary()
	{
		return ((this.x == 0) || (this.y == 0) || (this.x == getMinesweeper().getWidth()) || (this.y == getMinesweeper().getHeight()));
	}

	public MinesweeperGraphicsPointer generateGraphicsPointer()
	{
		if ((getMinesweeper().getInformation().getGameState() == MinesweeperGameState.DEAD) && this.mine)
		{
			return MinesweeperGraphicsIndex.MINE;
		}
		if (this.open)
		{
			int neighbours = neighbours().countMines();
			if (neighbours == 0)
			{
				return MinesweeperGraphicsIndex.EMPTY;
			}
			else
			{
				return MinesweeperGraphicsIndex.NUMBERS.getIndex(neighbours);
			}
		}
		else
		{
			return getMark().getGraphicsPointer();
		}
	}

	public Image generateImage()
	{
		return generateGraphicsPointer().getImage();
	}

	public String getAscii()
	{
		return generateGraphicsPointer().getAscii();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + this.x;
		result = prime * result + this.y;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		MinesweeperTile other = (MinesweeperTile) obj;
		if (this.x != other.x) return false;
		if (this.y != other.y) return false;
		return true;
	}
}
