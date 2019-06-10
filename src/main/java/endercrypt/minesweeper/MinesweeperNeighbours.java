package endercrypt.minesweeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import endercrypt.minesweeper.utility.ElementPuller;

public class MinesweeperNeighbours extends MinesweeperChild
{
	private final MinesweeperTile tile;
	private final List<MinesweeperTile> surrounding;

	protected MinesweeperNeighbours(MinesweeperTile tile)
	{
		super(tile.getMinesweeper());
		this.tile = tile;

		List<MinesweeperTile> surrounding = new ArrayList<>();
		for (int x = tile.getX() - 1; x <= tile.getX() + 1; x++)
		{
			for (int y = tile.getY() - 1; y <= tile.getY() + 1; y++)
			{
				if (getMinesweeper().isInside(x, y) == false)
				{
					continue;
				}
				MinesweeperTile surroundingTile = getMinesweeper().get(x, y);
				if (surroundingTile.equals(this.tile))
				{
					continue;
				}
				surrounding.add(surroundingTile);
			}
		}
		this.surrounding = Collections.unmodifiableList(surrounding);
	}

	public MinesweeperTile getCenter()
	{
		return this.tile;
	}

	public int countMines()
	{
		return (int) this.surrounding.stream().filter(t -> t.isMine()).count();
	}

	public ElementPuller<MinesweeperTile> tiles()
	{
		return new ElementPuller<>(this.surrounding);
	}
}
