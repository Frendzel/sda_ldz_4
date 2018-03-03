package pl.erbel;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.containsAny;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.validator.routines.EmailValidator.getInstance;
import static org.apache.log4j.Logger.getLogger;

@Component
public class JednorozecItemReadListener
        implements ItemReadListener<Jednorozec> {

    private static final Logger LOGGER = getLogger(JednorozecItemReadListener.class);

    private static final String[] NOT_ALLOWED_GENDERS = {"Shemale"};

    EmailValidator emailValidator = getInstance();

    @Override
    public void beforeRead() {
    }

    @Override
    public void afterRead(Jednorozec item) {
        try {
            assertItem(item);
            LOGGER.info(item);
        } catch (JednorozecException e) {
            LOGGER.error(item);
        }
    }

    private void assertItem(Jednorozec item) throws JednorozecException {
        if (isEmpty(item.getImie())
                || isEmpty(item.getNazwisko())
                || !validateEmail(item.getEmail())
                || item.getId() <= 0
                || containsAny(item.getPlec(), NOT_ALLOWED_GENDERS)) {
            throw new JednorozecException("Sth went wrong", item);
        }
    }

    private boolean validateEmail(String email) {
        return emailValidator.isValid(email);
    }

    @Override
    public void onReadError(Exception ex) {

    }
}
