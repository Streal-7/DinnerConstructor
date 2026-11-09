import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {
    private final HashMap<String, ArrayList<String>> dishesByType = new HashMap<>();
    private final Random random = new Random();


    void addDish(String type, String dishName) {
        ArrayList<String> dishes;
        String normalizedType = normalize(type);
        if (checkType(normalizedType)) {
            dishes = dishesByType.get(normalizedType);
        } else {
            dishes = new ArrayList<>();
            dishesByType.put(normalizedType, dishes);
        }
        dishes.add(dishName);
    }

    boolean checkType(String type) {
        if (type.isBlank()) {
            return false;
        }
        return dishesByType.containsKey(normalize(type));
    }

    boolean isEmpty() {
        return dishesByType.isEmpty();
    }

    ArrayList<String> getDishesByType(String type) {
        if (type.isBlank()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(dishesByType.getOrDefault(normalize(type), new ArrayList<>()));
    }

    private String normalize(String string) {
        if (string.isBlank()) {
            throw new IllegalArgumentException("Тип блюда не может быть пустым");
        }
        String trimmedString = string.trim();
        return trimmedString.substring(0, 1).toUpperCase() + trimmedString.substring(1).toLowerCase();
    }

    ArrayList<ArrayList<String>> generateDinnerList(int count, ArrayList<String> types) {
        ArrayList<ArrayList<String>> dinnerList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ArrayList<String> combo = new ArrayList<>();
            for (String type : types) {
                ArrayList<String> dishes = getDishesByType(type);
                int index = random.nextInt(dishes.size());
                combo.add(dishes.get(index));
            }
            dinnerList.add(combo);
        }
        return dinnerList;
    }
}
