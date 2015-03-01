/**
 * Created by Stephen Lazarionok.
 *
 * Represents the solution for the question
 * 'I open the first 7 drawers, all are empty. What is the probability there is a letter in the 8th drawer?'
 * by the
 */
object MonteCarloSolution extends ProblemDef {

    /**
     * A number of experiments
     */
    val EXPERIMENTS_COUNT = 1000000

    def firstSevenDrawersEmpty(c: Case) = !Range(0, 7).contains(c.letterLocation.getOrElse(-1))

    def main (args: Array[String]) {
        /**
         * An infinite sequence of random cases when first seven drawers (indexes 0 to 6) are empty
         */
        val randomCasesSream = Stream.continually(buildRandomCase)

        /**
         * An infinite sequence of random cases when first seven drawers are empty
         */
        val targetCasesStream = randomCasesSream.filter(firstSevenDrawersEmpty)

        /**
         * Take only those cases when the letter is in the 8th drawer (index 7)
         */
        val letterInSeventhDrawerCases = targetCasesStream.take(EXPERIMENTS_COUNT).filter(_ letterIn 7)

        println("Result : " + letterInSeventhDrawerCases.length / (EXPERIMENTS_COUNT * 1.0d))
    }
}
