package endercrypt.minesweeper.utility;

import java.util.Objects;

public class MinesweeperUtility
{
	public static String capitalize(String text)
	{
		Objects.requireNonNull(text);
		if (text.length() > 0)
		{
			char first_char = text.charAt(0);
			char upper_char = Character.toUpperCase(first_char);
			String ending = text.substring(1);
			return upper_char + ending;
		}
		return text;
	}

}
