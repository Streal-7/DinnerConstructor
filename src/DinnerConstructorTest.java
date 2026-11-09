import java.util.ArrayList;
import java.util.List;

public class DinnerConstructorTest {
    static void check(String name, boolean passed) {
        System.out.println((passed ? "✅ " : "❌ ") + name);
    }

    public static void main(String[] args) {


        DinnerConstructor dinnerConstructor = new DinnerConstructor();
        check("dinnerConstructor.isEmpty --> true", dinnerConstructor.isEmpty());
        check("новый конструктор: checkType(\"Первое\") --> false", !dinnerConstructor.checkType("Первое"));
        dinnerConstructor.addDish("Первое", "Борщ");
        check("checkType(Первое) --> true", dinnerConstructor.checkType("Первое"));
        check("dinnerConstructor.isEmpty после добавления --> false", !dinnerConstructor.isEmpty());
        dinnerConstructor.addDish("Второе", "Котлета");
        dinnerConstructor.addDish("Первое", "Солянка");
        check("getDishesByType(\"Первое\").size() --> 2", dinnerConstructor.getDishesByType("Первое").size() == 2);
        check("getDishesByType(\"Второе\").size() --> 1", dinnerConstructor.getDishesByType("Второе").size() == 1);
        check("getDishesByType(\"Десерт\") → пустой список", dinnerConstructor.getDishesByType("Десерт").isEmpty());
        check("checkType(Десерт) --> false", !dinnerConstructor.checkType("Десерт"));
        dinnerConstructor.getDishesByType("Первое").clear();
        check("Таблица после clear не изменилась getDishesByType(\"Первое\").size() --> 2", dinnerConstructor.getDishesByType("Первое").size() == 2);
        check("getDishesByType(\"первое\").size() --> 2", dinnerConstructor.getDishesByType("первое").size() == 2);
        check("checkType(\"  ПЕРВОЕ  \") → true", dinnerConstructor.checkType("  ПЕРВОЕ  "));
        dinnerConstructor.addDish("  второе ", "Плов");
        check("addDish(\"  второе \") --> «Второе» теперь 2 блюда", dinnerConstructor.getDishesByType("Второе").size() == 2);
        boolean thrown = false;
        try {
            dinnerConstructor.addDish("   ", "Борщ");
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        check("addDish(\"   \", ...) --> IllegalArgumentException", thrown);
        thrown = false;
        try {
            dinnerConstructor.addDish("", "Борщ");
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        check("addDish(\"\", ...) --> IllegalArgumentException", thrown);
        check("getDishesByType(\"\") --> пустой список", dinnerConstructor.getDishesByType("").isEmpty());
        check("getDishesByType(\"   \") --> пустой список", dinnerConstructor.getDishesByType("   ").isEmpty());
        check("checkType(\"\") --> false", !dinnerConstructor.checkType(""));
        check("checkType(\"   \") --> false", !dinnerConstructor.checkType("   "));

        DinnerConstructor single = new DinnerConstructor();
        single.addDish("Первое", "Борщ");
        ArrayList<String> types = new ArrayList<>();
        types.add("  первое ");
        ArrayList<ArrayList<String>> result = single.generateDinnerList(3, types);
        List<List<String>> expected = List.of(
                List.of("Борщ"),
                List.of("Борщ"),
                List.of("Борщ")
        );
        check("generateDinnerList(3, [\"  первое \"]) при одном блюде --> [[Борщ], [Борщ], [Борщ]]",
                expected.equals(result));

        DinnerConstructor twoTypes = new DinnerConstructor();
        twoTypes.addDish("Первое", "Борщ");
        twoTypes.addDish("Первое", "Солянка");
        twoTypes.addDish("Второе", "Котлета");
        twoTypes.addDish("Второе", "Плов");

        ArrayList<String> firstAndSecond = new ArrayList<>();
        firstAndSecond.add("первое");
        firstAndSecond.add("ВТОРОЕ");

        ArrayList<ArrayList<String>> combos = twoTypes.generateDinnerList(3, firstAndSecond);
        check("generateDinnerList(3, [первое, второе]) --> 3 комбинации", combos.size() == 3);

        boolean allHaveTwoDishes = true;
        for (ArrayList<String> combo : combos) {
            if (combo.size() != 2) {
                allHaveTwoDishes = false;
                break;
            }
        }
        check("каждая комбинация --> 2 блюда", allHaveTwoDishes);
        boolean allFromRightType = true;
        for (ArrayList<String> combo : combos) {
            if (!twoTypes.getDishesByType("Первое").contains(combo.get(0))) {
                allFromRightType = false;
                break;
            }
            if (!twoTypes.getDishesByType("Второе").contains(combo.get(1))) {
                allFromRightType = false;
                break;
            }

        }
        check("[первое, второе] --> блюдо 1 из «Первое», блюдо 2 из «Второе» --> true", allFromRightType);


        DinnerConstructor twoDishesSingleType = new DinnerConstructor();
        twoDishesSingleType.addDish("Первое", "Борщ");
        twoDishesSingleType.addDish("Первое", "Солянка");

        ArrayList<String> firstAndFirst = new ArrayList<>();
        firstAndFirst.add("первое");
        firstAndFirst.add("первое");

        ArrayList<ArrayList<String>> combos2 = twoDishesSingleType.generateDinnerList(2, firstAndFirst);
        check("generateDinnerList(2, [первое, первое]) --> 2 комбинации", combos2.size() == 2);

        boolean allHaveTwoDishesSingleType = true;
        for (ArrayList<String> combo : combos2) {
            if (combo.size() != 2) {
                allHaveTwoDishesSingleType = false;
                break;
            }
        }
        check("[первое, первое] --> каждая комбинация 2 блюда", allHaveTwoDishesSingleType);
        allFromRightType = true;
        for (ArrayList<String> combo : combos2) {
            if (!twoDishesSingleType.getDishesByType("Первое").contains(combo.get(0))) {
                allFromRightType = false;
                break;
            }
            if (!twoDishesSingleType.getDishesByType("Первое").contains(combo.get(1))) {
                allFromRightType = false;
                break;
            }

        }
        check("[первое, первое] --> блюдо 1 из «Первое», блюдо 2 из «Первое» --> true", allFromRightType);

    }
}





