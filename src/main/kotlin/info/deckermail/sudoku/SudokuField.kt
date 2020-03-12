package info.deckermail.sudoku

data class SudokuField(
    val x: Int,
    val y: Int,
    var value: Int? = null,
    val possibleValues: HashSet<Int> = HashSet<Int>()
        .apply {
            addAll(arrayOf(1,2,3,4,5,6,7,8,9))
        }
)
