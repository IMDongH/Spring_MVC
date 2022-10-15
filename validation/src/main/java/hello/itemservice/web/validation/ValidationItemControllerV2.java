package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        // errors 관련 내용이 안넘어감 errors 객체 선언도 안해주기 때문에 null 임
        // 그래서 errors?. 로 진행
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //bindingResult 의 위치는 @ModelAttribute 바로 다음에 와야한다. - 중요하다.
        //item 에 binding 된 결과가 bindingResult 에 담긴다.

        //검증 요류 결과 보관
//        Map<String, String> errors = new HashMap<>();

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){//아이템 이름이 비어있으면
//            errors.put("itemName","상품 이름은 필수입니다.");
            bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다."));
        }

        if(item.getPrice()==null || item.getPrice()<1000 || item.getPrice()>1000000)
        {
//            errors.put("price","가격은 1,000 ~ 1,000,000 까지 허용합니다.");
            bindingResult.addError(new FieldError("item", "price", "가격은 1,000 ~ 1,000,000 까지 허용합니다."));
        }

        if(item.getQuantity()==null || item.getQuantity() >=9999)
        {
//            errors.put("quantity", "수량은 최대 9,999까지 허용합니다.");
            bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9,999까지 허용합니다."));
        }

        //복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() !=null ){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice<10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값  = " + resultPrice);
                //글로벌 에러의 경우 ObjectError 사용 - 필드가 없으므로 field 는 파라미터로 안들어가도된다
                bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값  = " + resultPrice));
            }
        }

        //검증 실패시 원래 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("erros={}",bindingResult);
//            bindingResult는 model 에 자동으로 넘어 가기 때문에 model에 안담아도된다.
//            model.addAttribute("errors",bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //데이터가 증발하는 것을 해결해준다.

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){//아이템 이름이 비어있으면
            bindingResult.addError(new FieldError("item", "itemName",item.getItemName(),false,null,null, "상품 이름은 필수입니다."));
        }

        if(item.getPrice()==null || item.getPrice()<1000 || item.getPrice()>1000000)
        {
            bindingResult.addError(new FieldError("item", "price",item.getPrice(),false,null,null, "가격은 1,000 ~ 1,000,000 까지 허용합니다."));
        }

        if(item.getQuantity()==null || item.getQuantity() >=9999)
        {
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(),false,null,null,"수량은 최대 9,999까지 허용합니다."));
        }

        //복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() !=null ){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice<10000){
                //글로벌 에러의 경우 ObjectError 사용 - 필드가 없으므로 field 는 파라미터로 안들어가도된다
                //데이터가 넘어오는 것이 아니기 때문에 따로 rejectedValue 를 지정해주지 않아도된다.
                bindingResult.addError(new ObjectError("item",null,null, "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값  = " + resultPrice));
            }
        }

        //검증 실패시 원래 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("erros={}",bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //데이터가 증발하는 것을 해결해준다.

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){//아이템 이름이 비어있으면
            bindingResult.addError(new FieldError("item", "itemName",item.getItemName(),false,new String[]{"required.item.itemName"},null, null));
        }

        if(item.getPrice()==null || item.getPrice()<1000 || item.getPrice()>1000000)
        {
            bindingResult.addError(new FieldError("item", "price",item.getPrice(),false,new String[]{"range.item.price"},new Object[]{1000,1000000}, null));
        }

        if(item.getQuantity()==null || item.getQuantity() >=9999)
        {
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(),false,new String[]{"max.item.quantity"},new Object[]{9999},null));
        }

        //복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() !=null ){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice<10000){
                //글로벌 에러의 경우 ObjectError 사용 - 필드가 없으므로 field 는 파라미터로 안들어가도된다
                //데이터가 넘어오는 것이 아니기 때문에 따로 rejectedValue 를 지정해주지 않아도된다.
                bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice}, null));
            }
        }

        //검증 실패시 원래 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("erros={}",bindingResult);
            return "validation/v2/addForm";
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}
