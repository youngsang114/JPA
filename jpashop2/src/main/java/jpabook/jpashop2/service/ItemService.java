package jpabook.jpashop2.service;

import jpabook.jpashop2.domain.item.Book;
import jpabook.jpashop2.domain.item.Item;
import jpabook.jpashop2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = false)
    public void saveItem(Item item){
        itemRepository.save(item);
    }
    @Transactional // 변경 감지 기능!!!
    public void updateItem(Long itemid, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemid);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }
    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }
}
