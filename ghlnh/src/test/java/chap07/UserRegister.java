package chap07;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;

    private EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker passwordChecker, UserRepository userRepository, EmailNotifier emailNotifier){
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String pw, String email){
        //약한 비밀번호여부 확인
        if (passwordChecker.checkPasswordWeak(pw)){
            throw new WeakPasswordException();
        }
        //중복 id 여부 확인
        User user = userRepository.findById(id);
        if(user != null) {
            throw new DupIdException();
        }
        userRepository.save(new User("id", "pw", "email"));

        //가입 성공 시 이메일 전송
        emailNotifier.sendReisterEmail(email);
    }
}
