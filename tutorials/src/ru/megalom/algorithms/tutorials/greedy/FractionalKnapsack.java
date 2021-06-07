package ru.megalom.algorithms.tutorials.greedy;

import java.util.Arrays;

// Пример жадного алгоритма для задачи с оптимальным наполнением рюкзака продукатми,
// при условии что можно делить продукты на части
public class FractionalKnapsack {
    public static void main(String[] args) {
        float knapsackCapacity=7.0F;
        Meal[] meals= {
                new Meal("3",3,18),
                new Meal("2",2,14),
                new Meal("4",4,20),
        };
        System.out.println("Размер рюкзака: "+knapsackCapacity);
        System.out.println("Даны следующий продукты:\n"+Arrays.toString(meals));
        System.out.println("Максимум каллорий из продуктов, помещающихся в рюкзаке: "+
                getMaxCaloriesToKnapsack(meals,knapsackCapacity));
    }
    // Возвращает максимальное количество калорий из продуктов, которые можно поместить в рюкзак
    // с учетом возможности разделить продукт на части
    // O(n*log(n))
    public static float getMaxCaloriesToKnapsack(Meal[] meals,float knapsackCapacity){
        float maxCalories=0.0F;
        // O(n*log(n))
        Arrays.sort(meals);
        System.out.println("Отсортированные продукты: \n"+ Arrays.toString(meals));
        // В цикле проходим по всем продуктам, отсортированным по количеству калорий в ед. веса
        // и добавляем продукты (калории) в рюкзак
        //O(n)
        for(int i=0;i<meals.length;i++){

            if(meals[i].getWeight()>knapsackCapacity) {
                //Вес продукта больше вместимости рюкзака,
                // поэтому берем часть продукта(добавляем калории в рюкзак) и выходим из цикла
                if(knapsackCapacity>0)
                    maxCalories+=meals[i].getCaloriesPerWeight(knapsackCapacity);
                break;
            }
            //берем продукт целиком
            knapsackCapacity-=meals[i].getWeight(); // уменьшаем место в рюкзаке
            maxCalories+=meals[i].getCalories(); // увеличиваем количество калорий в рюкзаке
        }

        return maxCalories;
    }
    // Класс продукта, содержащий поля имя, вес, калории и вычисляющий ценность продукта(в калориях)
    public static class Meal implements Comparable<Meal>{
        private String name;
        private float weight;
        private float calories;
        public Meal(String name,float weight,float calories){
            this.name=name;
            this.weight=weight;
            this.calories = calories;
        }
        public float getValuePerWeight(){
            return calories /weight;
        }

        public float getCaloriesPerWeight(float weight){
            return calories /weight;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public float getCalories() {
            return calories;
        }

        public void setCalories(float calories) {
            this.calories = calories;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Meal{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", calories=" + calories +
                    '}'+"\n";
        }

        @Override
        public int compareTo(Meal o) {
            if(this.getValuePerWeight()>o.getValuePerWeight())
                return -1;
            if(this.getValuePerWeight()<o.getValuePerWeight())
                return 1;
            return 0;
        }
    }
}
