package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JednorozecItemWriteListener implements ItemWriteListener<Jednorozec> {

    private static final Logger LOGGER = Logger.getLogger(JednorozecItemWriteListener.class);

    @Override
    public void beforeWrite(List<? extends Jednorozec> items) {}

    @Override
    public void afterWrite(List<? extends Jednorozec> items) {
        items.forEach(LOGGER::info);
    }

    @Override
    public void onWriteError(Exception exception, List<? extends Jednorozec> items) {

    }
}
