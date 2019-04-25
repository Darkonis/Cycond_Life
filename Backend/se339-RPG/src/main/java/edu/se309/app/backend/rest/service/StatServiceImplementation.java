package edu.se309.app.backend.rest.service;

import edu.se309.app.backend.rest.entity.UserStat;
import edu.se309.app.backend.rest.repository.UserStatRepository;
import edu.se309.app.backend.rest.service.interfaces.StatService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Stat Service
 */
@Service
public class StatServiceImplementation extends BaseServiceImplementation<UserStat, Integer, UserStatRepository>
        implements StatService {

    /**
     * Constructor for the Stat Service
     *
     * @param userStatRepository Repository associated with this service
     */
    @Autowired
    public StatServiceImplementation(UserStatRepository userStatRepository) {
        super(userStatRepository);
    }

    /**
     * Incrememnt stat by amount
     * @param id id of associated account
     * @param stat stat to be incremented
     * @param amount amount to incremented
     * @return stat that was changed
     */
    @Override
    @Transactional
    public UserStat incrementByAmount(int id, String stat, int amount) {
        UserStat userStat = findById(id);
        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(userStat);
        return setStatValue(myAccessor, userStat, stat, ((Integer) myAccessor.getPropertyValue(stat)) + amount);
    }

    /**
     * Increment stat by one
     * @param id id of associated account
     * @param stat stat to be incremented
     * @return stat that was changed
     */
    @Override
    @Transactional
    public UserStat incrementByOne(int id, String stat) {
        return incrementByAmount(id, stat, 1);
    }

    private boolean isStat(String stat) {
        if (stat.equals("statId") || stat.equals("account")) {
            return false;
        }
        return true;
    }

    private UserStat setStatValue(PropertyAccessor myAccessor, UserStat userStat, String stat, int value) {
        if (isStat(stat) && myAccessor.isReadableProperty(stat) && myAccessor.getPropertyType(stat) == int.class) {
            myAccessor.setPropertyValue(stat, value);
            save(userStat);
            return userStat;
        } else {
            throw new ServiceException("Invalid request: No such stat found: " + stat);
        }
    }

    /**
     * Update stat with given value
     * @param id ID of associated account
     * @param stat stat to be updated
     * @param value value to change stat to
     * @return stat that was changed
     */
    @Override
    @Transactional
    public UserStat updateUserStat(int id, String stat, int value) {
        UserStat userStat = findById(id);
        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(userStat);
        return setStatValue(myAccessor, userStat, stat, value);
    }

    /**
     * Get user stats associated with username
     * @param username username of the associated account
     * @return stats linked to the username
     */
    @Override
    @Transactional
    public UserStat getByUsername(String username) {
        Optional<UserStat> userStat = getRepository().findByAccountUsername(username);
        return nullCheck(userStat, "Invalid request: no stats found for username: " + username);
    }

}
