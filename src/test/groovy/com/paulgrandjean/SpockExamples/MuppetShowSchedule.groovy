package com.paulgrandjean.SpockExamples

import static Muppets.*

/**
 * Define the 'personnel' required for one episode of the Muppet Show.
 * Defines all muppets and other stars who appear by defining a 'roster' of names.
 * Also defines a line-up which is the list of how each muppet or guest start appears in order during the show.
 * Master of ceremonies is defined, defaulting to Kermit
 * The critics are defined, defaulting to Statler and Waldorf
 * The rock band is defined, defaulting to Electric Mayhem
 * The orchestra is defined, defaulting to the Muppet Orchestra
 *
 * Certain rules are enforced.
 *  - Muppets and guest stars cannot be in the line-up without first being on the roster.
 *  - The guest star must not be empty
 *  - The master of ceremonies must be in both the roster and the lineup since he/she will be in every show
 *  - The critics must be in both the roster and the lineup since they also will be in every show.
 *  - The orchestra must be in both the roster and the lineup.
 *  - The rock band does not need to be in the show, so may, or may not, be in the lineup
 *  - The line-up can have any order of muppets and muppets can (and some usually will) be duplicated.  For example,
 *      the master of ceremonies must appear multiple times, as would the stage manager (typically the muppet "Scooter")
 *  - The line-up must start with either the master of ceremonies, or the orchestra.  This is for introducing the show
 *      opening with the theme song.
 *
 *  - The mid-show soap-opera-skit will either be Pigs in Space or Dr. Bob.  Each of these have predefined muppets,
 *      however each of these muppets must also appear on the roster and in the line-up.
 *
 * @author Paul Grandjean
 * @since 9/28/15
 * @version 1.0alpha
 */
public class MuppetShowSchedule {

    private String masterOfCeremonies = KERMIT
    private String orchestra = MUPPET_ORCHESTRA
    private String guestStar
    private String rockBand = ELECTRIC_MAYHEM
    private List<String> roster = new ArrayList<>()
    private List<String> lineup = new ArrayList<>()
    private String critic1 = STATLER
    private String critic2 = WALDORF

    // TODO: Need a new classes to define the skits
    // private SoapOperaSkit soapOperaSkit
    // private GuestStarSkit guestStarSkit1
    // private GuestStarSkit guestStarSkit2

    MuppetShowSchedule() {
        roster.add(KERMIT)
        roster.add(MUPPET_ORCHESTRA)
        roster.add(STATLER)
        roster.add(WALDORF)
    }

    public String getMasterOfCeremonies() {
        return masterOfCeremonies
    }

    public void setMasterOfCeremonies(String masterOfCeremonies) {
        this.masterOfCeremonies = masterOfCeremonies
    }

    public String getOrchestra() {
        return orchestra
    }

    public void setOrchestra(String orchestra) {
        this.orchestra = orchestra
    }

    public String getGuestStar() {
        return guestStar
    }

    public void setGuestStar(String guestStar) {
        this.guestStar = guestStar
        roster.add(guestStar)
    }

    public String getRockBand() {
        return rockBand
    }

    public void setRockBand(String rockBand) {
        this.rockBand = rockBand
    }

    public void addToRoster(String muppet) {
        roster.add(muppet)
    }

    public void addToLineup(String muppet) {
        if (roster.contains(muppet)){
            lineup.add(muppet)
        }
        else {
            throw new RuntimeException("Muppet must be on roster before adding to line-up.")
        }
    }

    public void removeFromRoster(String muppet) {
        if (lineup.contains(muppet)){
            throw new RuntimeException("Muppet must first be removed from the Line-Up.")
        }
        roster.remove(roster.indexOf(muppet))
    }

    public void removeFromLineUp(String muppet) {
        lineup.remove(lineup.indexOf(muppet))
    }

    public void clearRoster() {
        roster.clear()
    }

    public void clearLineUp() {
        lineup.clear()
    }

    public boolean rosterIsEmpty() {
        return roster.isEmpty()
    }

    public boolean lineupIsEmpty() {
        return lineup.isEmpty()
    }

    public int getRosterSize() {
        return roster.size()
    }

    public int getLinupSize() {
        return lineup.size()
    }

    public boolean rosterContains(String muppet) {
        return roster.contains(muppet)
    }

    public boolean lineupContains(String muppet) {
        return lineup.contains(muppet)
    }

    public void addCriticsToLineUp() {
        lineup.add(critic1)
        lineup.add(critic2)
    }

    public void addRockBandToLineup() {
        if (rockBand == "") {
            throw new RuntimeException("Guest star must not be blank")
        }

        if (!rosterContains(rockBand)) {
            throw new RuntimeException("Guest star must be on roster")
        }
        lineup.add(rockBand)
    }

    public void addGuestStarToLineup() {
        if (guestStar == "") {
            throw new RuntimeException("Guest star must not be blank")
        }

        if (!rosterContains(guestStar)) {
            throw new RuntimeException("Guest star must be on roster")
        }
        lineup.add(guestStar)
    }
}
