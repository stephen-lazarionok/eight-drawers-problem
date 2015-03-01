import org.apache.commons.lang.math.RandomUtils

/**
 * Created by Stephen Lazarionok.
 *
 * Describes the problem definition.
 *
 * You have a chest of 8 drawers. With probability 1/2, you put
 * a letter in one of the drawers. With probability 1/2, you
 * don't put a letter in any drawer.
 */
trait ProblemDef {

    /**
     * Represents a situation of the given problem.
     */
    abstract sealed class Case {

        /**
         * Returns the index of a drawer that has the letter if exists.
         * @return
         */
        def letterLocation: Option[Int]

        /**
         * Checks if the letter is in drawer number <b>i</b>.
         * @param x
         * @return
         */
        def letterIn(x: Int):Boolean
    }

    /**
     * All the drawers are empty
     */
    case object AllDrawersEmpty extends Case {

        override def letterLocation: Option[Int] = None

        override def letterIn(x: Int) = false
    }

    /**
     * A letter in a drawer with index <b>index</b>
     * @param a drawer index where we put a letter. Might be from 0 to 7 including the boundaries.
     */
    case class LetterInDrawer(drawerIndex: Int) extends Case {
        require(drawerIndex >= 0 && drawerIndex <= 7, "A drawer index should belong to [0; 7] according to the problem")

        override def letterLocation: Option[Int] = Some(drawerIndex)

        override def letterIn(x: Int) = x == drawerIndex
    }

    def buildRandomCase = {
        // Generate a random int from 0 to 15 including boundaries.
        val x = RandomUtils.nextInt(16)
        if (x < 8) LetterInDrawer(x) else AllDrawersEmpty
    }
}
