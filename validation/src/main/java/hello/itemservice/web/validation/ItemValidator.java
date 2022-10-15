package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        //clazz로 넘어오는 타입과 Item이 같은 타입인지
        //Item == subItem - 자식 타입인지
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){//아이템 이름이 비어있으면
//            bindingResult.addError(new FieldError("item", "itemName",item.getItemName(),false,new String[]{"required.item.itemName"},null, null));
            errors.rejectValue("itemName", "required");
        }

        if(item.getPrice()==null || item.getPrice()<1000 || item.getPrice()>1000000)
        {
//            bindingResult.addError(new FieldError("item", "price",item.getPrice(),false,new String[]{"range.item.price"},new Object[]{1000,1000000}, null));
            errors.rejectValue("price", "range",new Object[]{1000,1000000},null);
        }

        if(item.getQuantity()==null || item.getQuantity() >=9999)
        {
//            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(),false,new String[]{"max.item.quantity"},new Object[]{9999},null));
            errors.rejectValue("quantity", "max",new Object[]{9999},null);
        }


        //복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() !=null ){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice<10000){
                //글로벌 에러의 경우 ObjectError 사용 - 필드가 없으므로 field 는 파라미터로 안들어가도된다
                //데이터가 넘어오는 것이 아니기 때문에 따로 rejectedValue 를 지정해주지 않아도된다.
//                bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice}, null));
                errors.reject("totalPriceMin",new Object[]{10000,resultPrice}, null);
            }
        }



    }
}
