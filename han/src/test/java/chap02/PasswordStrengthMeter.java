package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null) return PasswordStrength.INVALID;
        if (s.isEmpty()) return PasswordStrength.INVALID;

        boolean lengthEnough = s.length() >= 8;
        boolean containsNum = meetsContainingNumberCriteria(s);
        boolean containsUppercase = meetsContainingUppercaseCriteria(s);

        if (lengthEnough && !containsNum && !containsUppercase) return PasswordStrength.WEAK;
        if (!lengthEnough && containsNum && !containsUppercase) return PasswordStrength.WEAK;
        if (!lengthEnough && !containsNum && containsUppercase) return PasswordStrength.WEAK;

        if (!lengthEnough) return PasswordStrength.NORMAL;
        if (!containsNum) return PasswordStrength.NORMAL;
        if (!containsUppercase) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private Boolean meetsContainingNumberCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private Boolean meetsContainingUppercaseCriteria(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
