package hello.itemservice.domain.item.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        log.info("BasicItemController items - {}", model);
        List<Item> items = itemRepository.findAll();
        return "basic/items";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        log.info("BasicItemController item - itemId = {}", itemId);
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        log.info("BasicItemController addForm");
        return "basic/addForm";
    }

//    @PostMapping("/add")
//    public String addItemV1(@RequestParam String itemName,
//                            @RequestParam int price,
//                            @RequestParam Integer quantity,
//                            Model model) {
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
//
//        itemRepository.save(item);
//
//        model.addAttribute("item", item);
//
//        return "basic/item";
//    }

//    /**
//     * @ModelAttribute("item") Item item
//     * model.addAttribute("item", item); 자동 추가
//     */
//    @PostMapping("/add")
//    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
//        itemRepository.save(item);
//        //model.addAttribute("item", item); //자동 추가, 생략 가능
//        return "basic/item";
//    }

//    /**
//     * @ModelAttribute name 생략 가능
//     * model.addAttribute(item); 자동 추가, 생략 가능
//     * 생략 시 model에 저장되는 name은 클래스명 첫글자만 소문자로 등록 Item -> item
//     */
//    @PostMapping("/add")
//    public String addItemV3(@ModelAttribute Item item) {
//        itemRepository.save(item);
//        return "basic/item";
//    }

//    /**
//     * @ModelAttribute 자체 생략 가능
//     * mode.addAttribute(item) 자동 추가
//     */
//    @PostMapping("/add")
//    public String addItemV4(Item item) {
//        log.info("BasicItemController addItemV4 - item = {}", item);
//        itemRepository.save(item);
//        return "basic/item";
//    }

    /**
     * PRG - Post/Redirect/Get
     */
    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        log.info("BasicItemController editForm - itemId = {}, model = {}", itemId, model);
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        log.info("BasicItemController edit - itemId = {}, item = {}", itemId, item);
        itemRepository.update(itemId, item);
        return  "redirect:/basic/items/{itemId}";
    }

}
