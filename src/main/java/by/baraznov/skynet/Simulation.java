package by.baraznov.skynet;

import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation {
    private static final int DAYS = 100;
    private static final int LIMIT_FACTORY = 10;
    private static final int LIMIT_FACTION = 5;

    private static final List<String> ROBOT_COMPONENT = List.of("head", "torso", "hand", "feet");

    private static final BlockingQueue<String> factoryMap = new LinkedBlockingQueue<>();
    private static final ConcurrentHashMap<String, AtomicInteger> worldMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AtomicInteger> wednesdayMap = new ConcurrentHashMap<>();

    static {
        for(String component : ROBOT_COMPONENT) {
            worldMap.put(component, new AtomicInteger(0));
            wednesdayMap.put(component, new AtomicInteger(0));
        }

    }
    public static void main(String[] args) throws InterruptedException {
        Thread factoryThread = new Thread(new Factory());
        Thread wednesdayThread = new Thread(new Faction("Wednesday"));
        Thread worldThread = new Thread(new Faction("World"));

        factoryThread.start();
        wednesdayThread.start();
        worldThread.start();

        factoryThread.join();
        wednesdayThread.join();
        worldThread.join();
        System.out.println("Factory component - " + factoryMap);
        System.out.println("Wednesday component" + wednesdayMap);
        System.out.println("World component" + worldMap);
        System.out.println(calculateWinner());
    }



    static class Factory implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < DAYS; i++) {
                Random rand = new Random();
                int amountOfComponent = rand.nextInt(LIMIT_FACTORY) + 1;
                for(int j = 0; j < amountOfComponent; j++) {
                    try {
                        factoryMap.put(createComponent());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    static class Faction implements Runnable {
        private final String factionName;

        public Faction(String factionName) {
            this.factionName = factionName;
        }

        @Override
        public void run() {
            Random rand = new Random();
            for(int i = 0; i < DAYS; i++) {

                int amountOfComponent = rand.nextInt(LIMIT_FACTION) + 1;
                for(int j = 0; j < amountOfComponent; j++) {
                    String component;
                    try {
                        component = factoryMap.poll(20, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (component == null) {
                        continue;
                    }
                    switch (factionName) {
                        case "World":
                            worldMap.get(component).getAndIncrement();
                            break;
                        case "Wednesday":
                            wednesdayMap.get(component).getAndIncrement();
                            break;
                        default:
                            System.out.println("Unknown faction: " + factionName);
                            break;
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    private static String createComponent() {
        Random rand = new Random();
        return ROBOT_COMPONENT.get(rand.nextInt(ROBOT_COMPONENT.size()));
    }

    private static int calculateRobots(ConcurrentHashMap<String, AtomicInteger> map) {
        return Math.min(map.get("head").intValue(), Math.min(map.get("torso").intValue(), Math.min(map.get("hand").intValue() / 2, map.get("feet").intValue() / 2)));
    }
    private static String calculateWinner(){
        int amountOfWorldRobots = calculateRobots(worldMap);
        int amountOfWednesdayRobots = calculateRobots(wednesdayMap);
        if(amountOfWednesdayRobots == amountOfWorldRobots){
            System.out.println("Amount of robots - " + amountOfWednesdayRobots);
           return "DRAW";
        }else if(amountOfWednesdayRobots > amountOfWorldRobots){
            System.out.println("Amount of robots - " + amountOfWednesdayRobots);
            return "Wednesday WIN!!!";
        }else{
            System.out.println("Amount of robots - " + amountOfWorldRobots);
            return "World WIN!!!";
        }

    }


}
