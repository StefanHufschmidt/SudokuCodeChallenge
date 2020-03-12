package info.deckermail.sudoku

class SudokuFieldGroupValidator {

    fun validate(fields: Array<SudokuField>): Boolean {
        val availableValues = hashSetOf(1,2,3,4,5,6,7,8,9)
        fields.forEach {field ->
            if (field.value != null) {
                val value = field.value as Int
                if (availableValues.contains(value)) {
                    availableValues.remove(value)
                } else {
                    return false
                }
            }
        }
        return true
    }

}
