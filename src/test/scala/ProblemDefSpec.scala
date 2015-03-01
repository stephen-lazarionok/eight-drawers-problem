import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by Stephen Lazarionok.
 */
class ProblemDefSpec extends FlatSpec with Matchers with ProblemDef {

    val EXPERIMENTS_COUNT = 100000
    val DELTA = 0.03d

    "A letter" should "be in one of 8 drawers in 50%" in {

        val xs = Stream.continually(buildRandomCase)
            .take(EXPERIMENTS_COUNT)
            .filter(Range(0, 8) contains _.letterLocation.getOrElse(-1))

        val rate = xs.length / (EXPERIMENTS_COUNT * 1.00d)

        rate shouldBe 0.5 +- DELTA
    }

    it should "be nowhere in 50%" in {

        var xs = Stream.continually(buildRandomCase)
            .take(EXPERIMENTS_COUNT)
            .filter(_.letterLocation.isEmpty)

        val rate = xs.length / (EXPERIMENTS_COUNT * 1.00d)

        rate shouldBe 0.5 +- DELTA
    }

    it should "be in one of 8 drawers with the same probability" in {
        val cases = Stream.continually(buildRandomCase)
            .filter(_.letterLocation.getOrElse(-1) >= 0)
            .take(EXPERIMENTS_COUNT)

        val targetCases = List.range(0, 8).map(x => cases.filter(_.letterIn(x)).length / (EXPERIMENTS_COUNT * 1.00d))

        val combinations = for {
            (x, xIndex) <- targetCases.zipWithIndex
            (y, yIndex) <- targetCases.zipWithIndex
            if (xIndex != yIndex)
        } yield (x, y)

        combinations.foreach(pair => {
            pair._1 shouldBe pair._2 +- DELTA
        })
    }

    "Case 'AllDrawersEmpty'" should "have all the drawers empty" in {
        assert(AllDrawersEmpty.letterLocation == None)
        Range(-10, 10).foreach(x => assert(!AllDrawersEmpty.letterIn(x)))
    }

    "Case 'LetterInDrawer'" should "have only one letter in the proper drawer" in {

        val problemCase = LetterInDrawer(5)
        assert(AllDrawersEmpty.letterLocation == None)
        Range(-10, 10).foreach(x => {
            if (x != 5) assert(!problemCase.letterIn(x)) else assert(problemCase.letterIn(x))
        })
    }

    it should "throw IllegalArgumentException whe creating with index < 0" in {
        a [IllegalArgumentException] should be thrownBy {
            LetterInDrawer(-1)
        }
    }

    it should "throw IllegalArgumentException whe creating with index > 7" in {
        a [IllegalArgumentException] should be thrownBy {
            LetterInDrawer(8)
        }
    }


}
