import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DeRevolutionibus {
    private static final float MAX_ORBIT = 100000;
    private static final float MAX_TIME = 200000;
    private static final int MIN_PLANETS = 2;

    public static void main(String[] args){
        //check args length
        if(args.length != 1){
            klops();
        }
        try {
            //get arguments
            float day = Float.parseFloat(args[0]);
            Planet[] planets = loadPlanets();

            if(day != 0f){
                //do not perform calculations when result is default
                calcPositions(planets, day);
            }

            print(planets);

            System.exit(0);
        } catch (Exception e){
            klops();
        }
    }
    private static Planet[] loadPlanets(){
        List<Planet> planets = new ArrayList<>();
        List<Float> orbits = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            String line;
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                Planet newPlanet = new Planet(data[0], round(Float.parseFloat(data[1])), round(Float.parseFloat(data[2])));
                if(orbits.contains(newPlanet.orbitRadius) || newPlanet.orbitRadius > MAX_ORBIT || newPlanet.rotationTime > MAX_TIME) klops();
                planets.add(newPlanet);
            }
        } catch (IOException e) {
            klops();
        }

        if(planets.size() < MIN_PLANETS) klops();

        Planet[] planetArray = new Planet[planets.size()];
        planets.toArray(planetArray);

        Arrays.sort(planetArray, new SortbyOrbit());
        return planetArray;
    }

    private static void calcPositions(Planet[] planets, float day){
        for(int i = 0; i < planets.length - 1; i++){
            float realDay = day % planets[i].rotationTime;
            float percent = realDay / planets[i].rotationTime;

            float quarter = percent * planets[i].rotationTime;
            if(quarter < planets[i].rotationTime * 0.25){
                planets[i].x = -quarter;
            } else if(quarter >= planets[i].rotationTime * 0.25 && quarter < planets[i].rotationTime * 0.5){
                float value = (float) (quarter - planets[i].rotationTime * 0.25);
                planets[i].x = value;
            } else if(quarter >= planets[i].rotationTime * 0.5 && quarter < planets[i].rotationTime * 0.75){
                float value = (float) (quarter - planets[i].rotationTime * 0.5);
                planets[i].x = (float) (planets[i].rotationTime * 0.5 - value);
            } else {
                float value = (float) (quarter- planets[i].rotationTime * 0.75);
                planets[i].x = -value;
            }
            planets[i].x = round(planets[i].x);
        }
        Arrays.sort(planets, new SortbyX());
    }
    //method checks if planet should be displayed
    private static void print(Planet[] planets){
        float last = planets[0].x;
        System.out.print(planets[0].name);

        for(int i = 1; i < planets.length; i++){
            if(planets[i].x != last){
                System.out.print(", "+planets[i].name);
                last = planets[i].x;
            }
        }
    }

    private static float round(float a){
        return (float) (Math.round(a * 100.0) / 100.0);
    }
    // exit method
    private static void klops(){
        System.out.println("klops");
        System.exit(0);
    }

    static class Planet {
        public String name;
        public float rotationTime;
        public float orbitRadius;
        public float x;

        public Planet(String name, float time, float radius){
            this.name = name;
            rotationTime = time;
            orbitRadius = radius;
            x = -orbitRadius;
        }

        @Override
        public String toString() {
            return String.format("%s, %f, %f, %f", name, rotationTime, orbitRadius, x);
        }
    }
    //comparators
    static class SortbyOrbit implements Comparator<Planet>{
        public int compare(Planet a, Planet b){
            return Float.compare(b.orbitRadius, a.orbitRadius);
        }
    }
    static class SortbyX implements Comparator<Planet>{
        public int compare(Planet a, Planet b){
            int x = Float.compare(a.x, b.x); //compare by x
            if(x == 0) return Float.compare(a.orbitRadius, b.orbitRadius); //if there is conjunction of the planets, find planet with biggest orbit
            return x;
        }
    }
}
