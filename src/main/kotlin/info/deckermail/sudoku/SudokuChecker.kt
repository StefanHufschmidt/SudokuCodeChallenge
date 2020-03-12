package info.deckermail.sudoku

class SudokuChecker(private val fieldGroupValidator: SudokuFieldGroupValidator = SudokuFieldGroupValidator()) {
    fun checkBoard(board: SudokuBoard): Boolean {
        val blocks = board.readBlocks()
        blocks.forEach { block ->
            if (!fieldGroupValidator.validate(block)){
                return false
            }
        }

        for (i in 1..9) {
            val row = board.readRow(i)
            if (!fieldGroupValidator.validate(row.toTypedArray())) {
                return false
            }

            val column = board.readColumn(i)
            if (!fieldGroupValidator.validate(column.toTypedArray())) {
                return false
            }
        }
        return true
    }

}
