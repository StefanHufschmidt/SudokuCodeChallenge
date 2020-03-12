package info.deckermail.sudoku

class SudokuBoard {

    val fields: ArrayList<SudokuField> = ArrayList()

    init {
        for (x in 1..9) {
            for(y in 1..9) {
                fields.add(SudokuField(x, y))
            }
        }
    }

    fun readRow(rowNum: Int): List<SudokuField> {
        return fields.filter { field ->
            field.y == rowNum
        }.sortedBy { field ->
            field.x
        }
    }

    fun readColumn(columnNum: Int): List<SudokuField> {
        return fields.filter { field ->
            field.x == columnNum
        }.sortedBy { field ->
            field.y
        }
    }

    fun readBlocks(): Array<Array<SudokuField>> {
        val blocksHolder = HashSet<HashSet<SudokuField>>()

        val oneOne = HashSet<SudokuField>()
        val oneTwo = HashSet<SudokuField>()
        val oneThree = HashSet<SudokuField>()
        val twoOne = HashSet<SudokuField>()
        val twoTwo = HashSet<SudokuField>()
        val twoThree = HashSet<SudokuField>()
        val threeOne = HashSet<SudokuField>()
        val threeTwo = HashSet<SudokuField>()
        val threeThree = HashSet<SudokuField>()

        for (row in 1..9) {
            val fieldsInRow = readRow(row)
            val firstThree = fieldsInRow.filter { it.x in 1..3 }
            val secondThree = fieldsInRow.filter { it.x in 4..6 }
            val thirdThree = fieldsInRow.filter { it.x in 7..9 }
            when (row) {
                in 1..3 -> {
                    oneOne.addAll(firstThree)
                    oneTwo.addAll(secondThree)
                    oneThree.addAll(thirdThree)
                }
                in 4..6 -> {
                    twoOne.addAll(firstThree)
                    twoTwo.addAll(secondThree)
                    twoThree.addAll(thirdThree)
                }
                in 7..9 -> {
                    threeOne.addAll(firstThree)
                    threeTwo.addAll(secondThree)
                    threeThree.addAll(thirdThree)
                }
            }
        }

        blocksHolder.addAll(
            arrayOf(
                oneOne,
                oneTwo,
                oneThree,
                twoOne,
                twoTwo,
                twoThree,
                threeOne,
                threeTwo,
                threeThree
            )
        )

        return blocksHolder
            .map { entry -> entry.toTypedArray() }
            .toTypedArray()
    }
}
