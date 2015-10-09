package com.paulgrandjean.SpockExamples

import spock.lang.Shared
import spock.lang.Specification

import static Muppets.*

/*
 * Example Test to demonstrate basic features of Spock.
 * The scenario is a test of the MuppetShowSchedule class.  See the javadoc for MuppetShowSchedule class for the
 * interface and business rules being testing.
 */

class MuppetShowScheduleTest extends Specification {

    @Shared
    MuppetShowSchedule schedule = new MuppetShowSchedule()

    // *** Fixture Methods ***

    // Fixture method setupSpec() runs before all other spec methods
    // Cannot access instance fields
    def setupSpec() {

        // Set the show's roster
        schedule.addToRoster(KERMIT)
        schedule.addToRoster(MUPPET_ORCHESTRA)
        schedule.addToRoster(PIGGY)
        schedule.addToRoster(ELECTRIC_MAYHEM)
        schedule.addToRoster(ROWLF_THE_DOG)
        schedule.addToRoster(SCOOTER)
        schedule.addToRoster(SAM_THE_EAGLE)

    }

    // Fixture method cleanupSpec() runs after all other spec methods
    // Cannot access instance fields
    def cleanupSpec() {
        schedule.clearRoster()
        print("DONE:  Show's Roster is cleared.")
        assert schedule.rosterIsEmpty()
    }


    // Fixture method setup() runs before each feature method.
    // TODO:  Perhaps can use data-driver testing here to setup different guest stars.
    def setup() {

        // TODO: Create a data-driven test that uses different guests.
        schedule.setGuestStar("David Bowie")

        schedule.addToLineup(KERMIT)
        schedule.addToLineup(MUPPET_ORCHESTRA)
        schedule.addToLineup(KERMIT)
        schedule.addToLineup(SCOOTER)
        schedule.addGuestStarToLineup()
        schedule.addRockBandToLineup()
        schedule.addToLineup(KERMIT)
        schedule.addToLineup(PIGGY)
        schedule.addToLineup(ROWLF_THE_DOG)
        schedule.addToLineup(SAM_THE_EAGLE)
        schedule.addToLineup(KERMIT)
        schedule.addToLineup(SCOOTER)
    }

    // Fixture method cleanup() runs after each feature method.
    def cleanup() {
        schedule.clearLineUp()
        assert schedule.lineupIsEmpty()
    }

    def "The lineup has at least 2 muppets"() {
        given:
            !schedule.lineupIsEmpty()

        expect:
            schedule.getLinupSize() > 1
    }

    def "The MC must be on the roster and the first or second in the lineup"() {
        given:
            String masterOfCeremonies = schedule.getMasterOfCeremonies()
            masterOfCeremonies != ""
        expect:
            schedule.rosterContains(masterOfCeremonies)
        and:
            schedule.lineupContains(masterOfCeremonies)
    }

//    def "Adding new muppet, and switching 2nd scheduled muppet to 7th doesn't remove master of ceremonies"() {
//        given: "A new muppet is added to the schedule"
//            scheduledMuppets.add(newMuppet)
//            println("Adding " + newMuppet + " to the Schedule")
//        when: "The new muppet replaces the 2nd muppet and the replace muppet is inserted at position 7"
//            def formerSecondMuppet = lineUp.get(2)
//            println("Old lineup: " + lineUp)
//            lineUp.add(7, formerSecondMuppet)
//            lineUp[2] = newMuppet
//        then: "The lineup still contains the master of ceremonies"
//            lineUp.contains(masterOfCeremonies)
//        and: "Lineup position 2 has the new muppet"
//            lineUp.get(2) == newMuppet
//        and: "Lineup position 7 has the former 2nd position muppet"
//            lineUp.get(7) == formerSecondMuppet
//            println("New lineup: " + lineUp)
//        where: "3 new muppets are provided"
//            newMuppet << ["Chicken 1", "Snowth 1", "Snowth 2"]
//    }
}  