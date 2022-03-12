package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // HashMap은 실무에서는 사용하면 안됨
    private static long sequence = 0L; // long도 실무에서는 사용하면 안됨. AtomicLong을 사용

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values()); // 그냥 보내도 되지만 타입 및 한번 더 감싸기
    }

    public void update(Long itemId, Item updateParam) { // 정석으로 만들려면 Id를 사용 안하는 DTO로 하나 더 만들어서 하는 것이 맞음
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
