package com.gluonapplication.model;
import com.gluonapplication.model.choice.Choice;
import com.gluonapplication.model.company.Company;
import com.gluonapplication.model.company.CompanyFactory;
import com.gluonapplication.model.company.GameObserver;
import com.gluonapplication.model.scenario.Scenario;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

import static java.lang.Thread.sleep;
public class Game implements Runnable {

    public Company company;
    private final List<GameObserver> observers = new ArrayList<>();
    public void attach(GameObserver observer){
        observers.add(observer);
    }
    public void notifyAllObservers(){
        for (GameObserver observer : observers) {
            observer.update();
        }
    }
    public CompanyFactory companyFactory = new CompanyFactory();
    public ArrayList<Scenario> getScenarios() {
        return scenarios;
    }
    public ArrayList<Scenario> scenarios = new ArrayList<>();
    private final Queue<Scenario> scenarioQueue = new ArrayDeque<>();
    private LocalDate date = LocalDate.of(2020,1,1);
    private Scenario currentScenario;
    private boolean decisionRound = true;

    public void setCurrentScenario() {
        if (!scenarioQueue.isEmpty()) {
            this.currentScenario = scenarioQueue.poll();
            notifyAllObservers();
        }
    }

    public Scenario getCurrentScenario() {
        return currentScenario;
    }
    public Game(String companyType) {

        if (companyType.equals("SMALL")) {
            company = companyFactory.getSmallCompany();
        }
        if (companyType.equals("MEDIUM")) {
            company = companyFactory.getMediumCompany();
        }
        if (companyType.equals("HUGE")) {
            company = companyFactory.getHugeCompany();
        }

        buildScenarios();
        buildScenarioQueue();
        setCurrentScenario();
    }
    public Boolean gameDone() {
        return getCompany().getBudget().get() < 0;
    }

    public Company getCompany() {
        return this.company;
    }
    public LocalDate getDate() {
        return date;
    }
    private void increaseDate() {
        date = date.plusDays(1);
    }

    private void buildScenarioQueue() {

        Collections.shuffle(this.scenarios);
        for (Scenario scenario:getScenarios())
            if (scenario.getID() == null) {
                scenarioQueue.add(scenario);
            }
    }
    public void rebuildScenarioQueue(Integer scenarioID) {

        this.scenarios.remove(0);
        this.scenarioQueue.clear();
        for (Scenario scenario:getScenarios())
            if (scenario.getID() == null || scenario.getID() == scenarioID) {
                System.out.println(scenario.getScenarioText());
                scenarioQueue.add(scenario);
            }
    }
    private void buildScenarios() {

        Scenario coronaScenario = new Scenario("Scenario: \n" +
                "10 of your employees has caught the\n" +
                "china virus, what do you do?");
        coronaScenario.addChoice(new Choice("It's their own fault!\nFire them!", 1000., -0.5, -5.0, 0.0, "You save money, but oh my do people think\n your company has no morals"));
        coronaScenario.addChoice(new Choice("Send them home\nwith pay", -200.0, 0.5, 5.0, 90., "You lose money, but gain\nthe respect of the people"));
        coronaScenario.addChoice(new Choice("Blame China!", -5000., -2., -10., 0., "Oops. China does not take well to criticism.\nICBMs carrying nukes\n has arrived at your local shop", 1));
        coronaScenario.addChoice(new Choice("Do nothing!", -5000., 0., 0., 0., "Such a gutless CEO you are!"));
        this.scenarios.add(coronaScenario);

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
                "recommending the horny employee should go to a camp\n where they need to concentrate, \n" +
                "hopefully they will return..... more responsible"));
        computerVirus.addChoice(new Choice("S̴ta͡͝r҉̴t̶͘ ̷a͢n͏ ͏̷i͢͠ņ͟t̨e̴͟͢r͏͠ǹál̷ \nd͝͏̡a͢t͘i͘͜͞n̷g͡͏̛ se͟r̢͠v͟íc͘͞ę̵\n", 200., -2., -10., 0., "blablala"));
        computerVirus.addChoice(new Choice("Ḑ̵̗̠̪̭̾ͤ͛͌ͮ̽ͮͥ͂̓̄̓̋̾͐̓́͟ǫ͔̗̪̺͇͇̺̣ͬ̈̇ͨ͒ͯ̍ͅ ͂̔̇̈́͑͒͒͌͛͆ͤ̐̄̐̾ͦ̿̈̚҉̴͎͉̩̣̦͎̱̣̟͟͞n̖̥̙͈̼̦̭̜͇̒ͥ͛̓̏͒ͣ̂̄̔ͭ̓ͧ̑̀̈̒͌͟͢͝ͅͅͅö̵̢̨͔͍͚͈̗̺̙̜̰̱̲͎͙̞́ͥ̑ͤ̐͐́́t̵̸̛̩̺̮̙͕̼̊̃̔͆̇͞h̶̞̲̺͓̲͉̪͎̲̯̱̞̼̱̹̭͍͂̎͌̽̒̿ͯ̐̚̕̕͟͠i̱̠̳͙̪͚̥̼̝͛̊͌ͧ͒ͨ̓ͨ̿̔̑͢͠͡ņ̦̱̮͍̻͈̻͉̥̜̭͗͌̄̀̌͝ǵ̙̘̣̟̯̘̫̝̣̱ͥ̌ͩ̈ͯ̆̀́͞!̨͕͈̺̫̀̈ͦ͗̓̕\n", -2000., 0.1, -5., 0., "blablabla"));
        this.scenarios.add(computerVirus);

        Scenario chinaNuclearWar = new Scenario("Scenario: \n" +
                "Your reputation within the CCP\n" +
                "has been destroyed, what do you do?", 1);
        chinaNuclearWar.addChoice(new Choice("Make China\n Great Again", 10000., 1., -5.0, 0.0, "To show your support for china, you've decided to only use chinese products in the future, MAKE CHINA GREAT AGAIN!\n"));
        chinaNuclearWar.addChoice(new Choice("Blame China Again", -200.0, 0.5, 5.0, 90., "blabla"));
        chinaNuclearWar.addChoice(new Choice("Beg for forgiveness ", 200., -2., -10., 0., "blablala"));
        chinaNuclearWar.addChoice(new Choice("Do nothing!", -2000., 0.1, -5., 0., "blablabla"));
        this.scenarios.add(chinaNuclearWar);

        Scenario greenEnergy = new Scenario("Scenario:\n" +
                "After several years, you will find that more companies are\n starting to go green, and to your surprise,\n they are doing extremely satisfactorily, WWYD?");
        greenEnergy.addChoice(new Choice("Go green!",10000., 1., -5.0, 0.0, "bla"));
        greenEnergy.addChoice(new Choice("Pollution is a \nsocial construct",-200.0, 0.5, 5.0, 90., "blabla"));
        greenEnergy.addChoice(new Choice("Nuclear Power,\nbaby!", 200., -2., -10., 0., "you decided to go green... URANIUM GREEN!\n you know how people always offer\n" +
                "to lend an extra hand,\nwe'll be the first in the world to give them one!", null,
                "PowerPlant.png",
                41.5, 75,
                69.,132. ));
        greenEnergy.addChoice(new Choice("Do nothing!",-2000., 0.1, -5., 0., "blablabla"));
        this.scenarios.add(greenEnergy);

        Scenario bitcoinScenario = new Scenario("Scenario: \n" +
                "With inspiration from r/wallstreetbets,\n your most trusted advisor wants The Company\n to invest in Bitcoin to secure The Company’s\n economic future, what do you?");
        bitcoinScenario.addChoice(new Choice("Take a ride with BTC\n to the moon! (All in)",calculateReward((double) getCompany().getBudget().get()), 0.5, 5.0, 90., "blabla",
                "bitcoinMoon.png", "moonImage"));
        bitcoinScenario.addChoice(new Choice("Invest 10% of\nthe budget",calculateReward((double) getCompany().getBudget().get() * 0.1), 0.5, 5.0, 90., "blabla"));
        bitcoinScenario.addChoice(new Choice("Fire them.\nThey are obviously\n incompetent",-200.0, 0.5, 5.0, 90., "blabla"));
        bitcoinScenario.addChoice(new Choice("Do nothing!",0., 0., 5.0, 90., "blabla"));
        this.scenarios.add(bitcoinScenario);

        Scenario sundayChurch = new Scenario("You might have spent too much time looking at \"research\" while working from home,\n maybe you should redeem your soul by going to church this sunday?", "church");
        sundayChurch.addChoice(new Choice("Don't go to church", -200., -1., 5., 90.,"You decided that sundays could be spent doing something more productive than praying to a imaginary friend\n" +
                "however since you decided not to go to church, God now decided to send you to hell!"));
        sundayChurch.addChoice(new Choice("Say a prayer", 100., 1., -5., 80., "blabla"));
        sundayChurch.addChoice(new Choice("Go to church", 100., 1., -5., 80., "blabla"));
        sundayChurch.addChoice(new Choice("Do nothing!", 100., 1., -5., 80., "blabla"));
    }
    public Double calculateReward(Double investedAmount) {

        Random random = new Random();
        Double multiplier = random.nextDouble() * 3 - 1;
        var result = investedAmount * multiplier;

        return result - investedAmount;
    }

    @Override
    public void run() {
        try {
            timer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void timer() throws InterruptedException {

        do {
            sleep(1000);
            if (decisionRound) {
                getCompany().updateBudget();
                getCompany().updateBudgetConstant();
                increaseDate();
                notifyAllObservers();
            }
        } while (!gameDone());
    }

    public void flipIsDecisionRound() {
        decisionRound = !decisionRound;
    }
}
