package info.deckermail.sudoku

class SudokuGenerator {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val initializer = SudokuInitializer()
            val board = initializer.initializeBoard()

            var rowCounter = 0
            for (i in 1..9) {
                if (rowCounter == 3) {
                    rowCounter = 0
                    println("---------------------------------")
                }
                val row = board.readRow(i)
                var itemCounter = 0
                for (field in row) {
                    if (itemCounter == 3) {
                        itemCounter = 0
                        print(" | ")
                    }
                    print("[" + (if(field.value != null) field.value else " ") + "]")
                    itemCounter++
                }
                println()
                rowCounter++
            }
        }
    }
}