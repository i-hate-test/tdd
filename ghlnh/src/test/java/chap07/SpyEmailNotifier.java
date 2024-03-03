package chap07;

public class SpyEmailNotifier implements EmailNotifier{
    private boolean called;
    private String email;

    public boolean isCalled(){
        return called;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public void sendReisterEmail(String email) {
        this.called = true;
        this.email = email;
    }
}
