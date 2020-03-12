package info.deckermail.sudoku

class SudokuInitializer {

    fun initializeBoard(): SudokuBoard {
        val board = SudokuBoard()
        var valuesSet = 0

        while (valuesSet < board.readBlocks().size * MAX_GIVEN_VALUES_PER_BLOCK) {
            val field = calculateNextFieldToSetValueAt(board)
            val newValue = field.possibleValues.random()
            field.value = newValue
            valuesSet ++
            board.readRow(field.y).forEach { it.possibleValues.remove(newValue) }
            board.readColumn(field.x).forEach { it.possibleValues.remove(newValue) }
            board.readBlocks().first { it.contains(field) }.forEach { it.possibleValues.remove(newValue) }
        }
        return board
    }

    private fun calculateNextFieldToSetValueAt(board: SudokuBoard): SudokuField {
        while (true) {
            // choose field to check
            val x = (1..9).random()
            val y = (1..9).random()
            val currentField = board.fields.first { it.x == x && it.y == y }

            // when field has already been set continue
            if (currentField.value != null) {
                continue
            }

            // when not already set, check if the block has already MAX_GIVEN_VALUES_PER_BLOCK
            val setValuesInBlockOfCurrentField = board.readBlocks()
                .first { it.contains(currentField) }
                .count { it.value != null }
            if (setValuesInBlockOfCurrentField >= MAX_GIVEN_VALUES_PER_BLOCK) {
                continue
            }

            // if not return coordinates of found field
            return currentField
        }
    }

    companion object {
        private const val MAX_GIVEN_VALUES_PER_BLOCK = 4
    }
}
