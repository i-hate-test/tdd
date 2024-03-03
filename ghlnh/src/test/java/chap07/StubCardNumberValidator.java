package chap07;

public class StubCardNumberValidator extends CardNumberValidator{
    private String invalidNo;

    private String theftNo;

    public void setInvalidNo(String invalidNo){
        this.invalidNo = invalidNo;
    }

    public void SetTheftNo(String theftNo) {
        this.theftNo = theftNo;
    }


    @Override
    public CardValidity validate(String cardNumber){
        if(invalidNo !=null && invalidNo.equals(cardNumber)){
            return CardValidity.INVALID;
        }

        if(theftNo !=null && theftNo.equals(cardNumber)){
            return CardValidity.THEFT;
        }

        return CardValidity.VALID;
    }

    //StubCardNumberValidator는 setInvalidNo()로 지정한 카드 번호에 대해서는 INVALID를 리턴하고 그 외 나머지는 VALID 리턴 하는 역할
}
