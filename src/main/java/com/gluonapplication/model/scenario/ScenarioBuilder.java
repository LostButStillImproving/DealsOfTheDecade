package com.gluonapplication.model.scenario;

import com.gluonapplication.model.Game;
import com.gluonapplication.model.choice.Choice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class ScenarioBuilder {

    Game game;
    public ArrayList<Scenario> scenarios = new ArrayList<>();
    private final Queue<Scenario> scenarioQueue = new ArrayDeque<>();


    public ScenarioBuilder(Game game) {

        this.game = game;
        buildScenarios();
        buildScenarioQueue();

    }
    private void buildScenarioQueue() {

        //Collections.shuffle(this.scenarios);
        for (Scenario scenario:this.scenarios)
            if (scenario.getID() == null) {
                scenarioQueue.add(scenario);
            }
    }

    public void rebuildScenarioQueue(Integer scenarioID) {

        this.scenarios.remove(0);
        this.scenarioQueue.clear();
        for (Scenario scenario:this.scenarios)
            if (scenario.getID() == null || scenario.getID() == scenarioID) {
                System.out.println(scenario.getScenarioText());
                scenarioQueue.add(scenario);
            }
    }
    private void buildScenarios() {

        Scenario coronaScenario = new Scenario("Scenario:\n" +
                "10 of your employees has caught the " +
                "china virus.\nWhat do you do?");
        coronaScenario.addChoice(new Choice("It's their own fault!\nFire them!", 1000., -0.5, -5.0, 0.0, "You save money, but oh my do people think\n your company has no morals"));
        coronaScenario.addChoice(new Choice("Send them home\nwith pay", -200.0, 0.5, 5.0, 90., "You lose money, but gain\nthe respect of the people"));
        coronaScenario.addChoice(new Choice("Blame China!", -5000., -2., -10., 0., "Oops. China does not take well to criticism.\nICBMs carrying nukes has arrived at your local shop", 1, "BlameChinaEvent.png", "cityGraphics"));
        coronaScenario.addChoice(new Choice("Do nothing!", -5000., 0., 0., 0., "Such a gutless CEO you are!"));
        this.scenarios.add(coronaScenario);

        Scenario chinaNuclearWar = new Scenario("Scenario:\n" +
                "Your reputation within the CCP\n" +
                "has been destroyed, what do you do?", 1);
        chinaNuclearWar.addChoice(new Choice("Make China\nGreat Again", 10000., 1., -5.0, 0.0, "To show your support for china, you've decided to only\n use chinese products in the future,\n MAKE CHINA GREAT AGAIN!\n", "City.png", "cityGraphics"));
        chinaNuclearWar.addChoice(new Choice("Blame China Again", -200.0, 0.5, 5.0, 90., "blabla"));
        chinaNuclearWar.addChoice(new Choice("Beg for forgiveness ", 200., -2., -10., 0., "blablala"));
        chinaNuclearWar.addChoice(new Choice("Do nothing!", -2000., 0.1, -5., 0., "If you can't make a real stance, why are you even here?"));
        this.scenarios.add(chinaNuclearWar);

        Scenario sundayChurch = new Scenario("Scenario:\n" +"You might have spent too much time looking at \"research\"\nwhile working from home,\n maybe you should redeem your soul\n by going to church this sunday?", "church");
        sundayChurch.addChoice(new Choice("Don't go to church", 0., -1., 5., 90.,"You decided that sundays could be spent doing\nsomething more productive than praying to a imaginary\nfriend ," +
                "however since you decided not to go to church,\nGod now decided to send you to hell!", "VolcanoEvent.png", "cityGraphics"));
        sundayChurch.addChoice(new Choice("Say a prayer", 100., 1., -5., 80., "*crickets*"));
        sundayChurch.addChoice(new Choice("Go to church", 100., 1., -5., 80., "*crickets*"));
        sundayChurch.addChoice(new Choice("Do nothing!", 100., 0., -5., 80., "*crickets"));
        this.scenarios.add(sundayChurch);

        Scenario computerVirus = new Scenario("A͌ͥ̔̈́̑̇͝ ̕mi̷͋ͤ͂n͂i͌̓̄͐m͋ͤ͒̋̓͏u͆̓̽̎̎̀m̊̓ͨ̊ ẘ͆" +
                "̔̈́ͧä̋̾͐̆g͛e͗̐ͭͪ̍ ̉͒ͩ̾̌ẅ́ͭ̃̿o͋ͩrͩ̽ͬͮ͑̌k͠ȅ̓̚̚͜r̸͋̈́̅͛̉ ̽h̉̌̇a̋̅́͏ś̔̇̑ͣͮ ̢͌ͬ̆́b̆̄̈́̇̀͗ͪê̇̇̄ͨ̕è̀̎͡nͦ͏ ͊lȯỏ͛͑͂k̉͒ͫͬ͞i̢ͫ̇̔͐̓̈" +
                "nͫ̿ͬ̍̌gͥ̏ͦͭ̆\n" +
                " ͂̃̈͏f͂ͨ̐ͧ͜őͧ̔҉r̨͐͌̓ ̀̚h̎̊͝ơ̓t̐̅ ͭͯ̎҉s̑͐ͯi̶̊ͪͪ͊n̶ͦ̈͆̏̑̚g̓̈́͆͒̚l̉̄͋ͣe c̓ͩh́̎̓i̸̎ͭč̷͒̄͛̌͛k\n" +
                "ͧ̏̈́͋͠ś ͩ̒͊̕i͋͛̒̂ͨ͊ͭn̏̏ͦ ͌̂ͭ̊̎́͌h̃̎͒͒͒i͌̄̆ͬ̾̔͋͏s̶ ̀n͏ė̀̍͗̊̽̈́ĭ̀ͣ̔gͣ͒ͤ͟h͐̅̚b͛̽̀ͩ̓͗̚͟o̍̍̾̑͜r̵h̴̉̈̒͗ͯͫ̚o" +
                "̓͋҉o̡̊̿̋dͤ͐͗ͩ̄̃̑ͯ̇̀ͦ̕n̋̇́ͮͫͩ͞,̨ͪ ͬ̀ͪ̂yͤ͋͒͂̅ͥ͜ǒ̏ͮ̾͊͑̾uͮͧ͌͐rͭ̇̈́ ͪ̆̀ĕ̈́̃͜nͥ̀̏͗̇͝t́̊͛́́ͣiͯ̌̋̑́͒͟ṙ̂́ͤ̒͌͑ë́̿̍ͥ͘ ͧ̍͒Ïͥ" +
                "̑̄T̵ ̐s͂͋ͩ̓̈̀̓͞y͗ͦͪ͟st̢̆̈̉̃ͬë̊̌̽͊͛̆͠m̊̆̏̏҉ \n̂͑͆h̾ͨ̉̄̉̐a̍̐ͤ̒̋̚s̅ͫͭ̔̃̆ ̶͊̽̔b͂́͋eͮ̽̈̓̾̍e̓̎n̾ͩ̆̇̀͑ ͗i̢nͨͭ̅͊̈̓" +
                "͋f͟ẻ͆̋͞cͤ̾͌͛̉ͪtͧͦ͒̋ȩͣ̔̒̔ͬ̚d̆̇͌̓ͧ͑ ͥ͋͂w̿͊̀̏͡i͋͑̎͗ͨ͐t́hͪ́ ͊́//ͥn͌͏a̚m̒ͣ͊͢êͭͤͪ̾ͮ/͑ͨ͛͜/̌ͬ̔͒ͨͫ̚͝,̎͑͛ͪ̿ͣ͑\nͪͨ̆ͩnͭ́ͯ͂" +
                " ̢ͦ͂̽̄̃w̵̆ͭ͊͂͊h̸ͣͪͣ̐a͊t̔͂͑̉ ͘d͒ͤ̏͛ͫ̚ǒ̍̋ͦͬ͠ ͒̅̓ͮy͊̿̾̆ͮ̂͆͢oͪ͗̽͏ű̒ͦ̍̕ ̌ͧd̈̅ͭ̈͂ͨ͝o͟?͜","2");
        computerVirus.addChoice(new Choice("Hi̕҉re͢͡͞ ̨͝ḩ̴̵o̢t̡́͢ ̨̡s̛͢in͝g͏l̡̢͡e҉s̴̛̛ ̷̵͢i͢͡͡n͢͢ \n̴t̷h͏̵è ͢ ̸n̡ȩ̢͡i̢g͏h̛͢b́͞our̷̴h͜oơ͞d̕", 10000., 1., -5.0, 0.0, "bla"));
        computerVirus.addChoice(new Choice("S̴͟ènd̶ ̷emp̡̢ĺ̸͘o͡y҉̀͡e̵̶e͝͏͝ \ņt̴ǫ͡ ͢h҉̷ǫ́r̶͡n̴̷̶y ̀j̨̢͞a̵͠i̵ļ\n", -200.0, 0.5, 5.0, 90., "You consult a doctor who quickly rid your SSD of STD\n as well as " +
                "recommending that the horny employee\n should go to a camp where they need to concentrate, \n" +
                "hopefully they will return..... more responsible"));
        computerVirus.addChoice(new Choice("S̴ta͡͝r҉̴t̶͘ ̷a͢n͏ ͏̷i͢͠ņ͟t̨e̴͟͢r͏͠ǹál̷ \nd͝͏̡a͢t͘i͘͜͞n̷g͡͏̛ se͟r̢͠v͟íc͘͞ę̵\n", 200., -2., -10., 0., "blablala"));
        computerVirus.addChoice(new Choice("Ḑ̵̗̠̪̭̾ͤ͛͌ͮ̽ͮͥ͂̓̄̓̋̾͐̓́͟ǫ͔̗̪̺͇͇̺̣ͬ̈̇ͨ͒ͯ̍ͅ ͂̔̇̈́͑͒͒͌͛͆ͤ̐̄̐̾ͦ̿̈̚҉̴͎͉̩̣̦͎̱̣̟͟͞n̖̥̙͈̼̦̭̜͇̒ͥ͛̓̏͒ͣ̂̄̔ͭ̓ͧ̑̀̈̒͌͟͢͝ͅͅͅö̵̢̨͔͍͚͈̗̺̙̜̰̱̲͎͙̞́ͥ̑ͤ̐͐́́t̵̸̛̩̺̮̙͕̼̊̃̔͆̇͞h̶̞̲̺͓̲͉̪͎̲̯̱̞̼̱̹̭͍͂̎͌̽̒̿ͯ̐̚̕̕͟͠i̱̠̳͙̪͚̥̼̝͛̊͌ͧ͒ͨ̓ͨ̿̔̑͢͠͡ņ̦̱̮͍̻͈̻͉̥̜̭͗͌̄̀̌͝ǵ̙̘̣̟̯̘̫̝̣̱ͥ̌ͩ̈ͯ̆̀́͞!̨͕͈̺̫̀̈ͦ͗̓̕\n", -2000., 0.1, -5., 0., "blablabla"));
        this.scenarios.add(computerVirus);

        Scenario greenEnergy = new Scenario("Scenario:\n" +
                "After several years, you will find that more companies are\n starting to go green, and to your surprise,\n they are doing extremely satisfactorily, WWYD?");
        greenEnergy.addChoice(new Choice("Go green!",10000., 1., -5.0, 0.0, "bla"));
        greenEnergy.addChoice(new Choice("Pollution is a \nsocial construct",-200.0, 0.5, 5.0, 90., "blabla"));
        greenEnergy.addChoice(new Choice("Nuclear Power,\nbaby!", -5000., -2., -10., 0., "you decided to go green... URANIUM GREEN!\n you know how people always offer\n" +
                "to lend an extra hand?\nWe'll be the first in the world to give them one!", null,
                "PowerPlant.png", "nuclearPowerImage",
                41.5, 75,
                69.,132. ));
        greenEnergy.addChoice(new Choice("Do nothing!",-2000., 0.1, -5., 0., "bla"));
        this.scenarios.add(greenEnergy);

        Scenario bitcoinScenario = new Scenario("Scenario:\n" +
                "With inspiration from r/wallstreetbets,\nyour most trusted advisor wants The Company\n to invest in Bitcoin to secure The Company’s\n economic future, what do you?");
        bitcoinScenario.addChoice(new Choice("Take a ride with BTC\n to the moon! (All in)","calculateReward", 0.5, 5.0, 90., "Following the spirit of r/wallstreetbets\n, you, a true autist decided to \n" +
               "ride Bitcoin to the moon, wise or not\n, you're in it for the tendies.",
               "bitcoinMoon.png", "nodeID", 0, 9, 0,0));
        bitcoinScenario.addChoice(new Choice("Invest 10% of\nthe budget","calculateReward10", 0.5, 5.0, 90., "blabla"));
        bitcoinScenario.addChoice(new Choice("Fire them.\nThey are obviously\n incompetent",-200.0, 0.5, 5.0, 90., "blabla"));
        bitcoinScenario.addChoice(new Choice("Do nothing!",0., 0., 5.0, 90., "The Company definitely lost out\n on a LOT of profits!\n....Surely.."));
        this.scenarios.add(bitcoinScenario);
    }

    public Queue<Scenario> getScenarioQueue() {
        return scenarioQueue;
    }
}
