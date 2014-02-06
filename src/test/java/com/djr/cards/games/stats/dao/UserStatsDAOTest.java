package com.djr.cards.games.stats.dao;

import com.djr.cards.data.entities.User;
import com.djr.cards.data.entities.UserStats;
import com.djr.cards.games.stats.UserStatsDAO;
import com.opensymphony.xwork2.interceptor.annotations.Before;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import static org.mockito.Mockito.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author djr4488
 *         Date: 2/5/14
 *         Time: 12:34 PM
 */
@RunWith (MockitoJUnitRunner.class)
public class UserStatsDAOTest extends TestCase {
	@Mock
	private EntityManager em;
	@Mock
	private Logger logger;
	@Mock
	private TypedQuery<UserStats> query;

	@InjectMocks
	private UserStatsDAO userStatsDAO = new UserStatsDAOImpl();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindStatsByUserStatsExist() {
		User user = new User();
		UserStats userStats = new UserStats();
		when(em.createNamedQuery("findUserStatsByUser", UserStats.class)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(userStats);
		UserStats returned = userStatsDAO.findStatsByUser(user, "golf", "12345");
		verify(logger).debug(any(String.class), any(User.class), any(String.class), any(String.class));
		verify(logger).debug(any(String.class), any(UserStats.class));
		assertEquals(userStats, returned);
	}

	@Test
	public void testFindStatsByUserStatsMissing() {
		User user = new User();
		UserStats userStats = new UserStats();
		when(em.createNamedQuery("findUserStatsByUser", UserStats.class)).thenReturn(query);
		when(query.getSingleResult()).thenThrow(new NoResultException("No user stats found!"));
		UserStats returned = userStatsDAO.findStatsByUser(user, "golf", "12345");
		verify(em).persist(any(UserStats.class));
		assertNotSame(userStats, returned);
	}

	@Test
	public void testLoadStatisticsExisting() {
		List<UserStats> userStatsList = new ArrayList<UserStats>();
		when(em.createNamedQuery("findUserStats", UserStats.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(userStatsList);
		List<UserStats> returned = userStatsDAO.loadStatistics("golf", "12345");
		assertNotNull(returned);
		assertEquals(userStatsList, returned);
	}

	@Test
	public void testLoadStatisticsMissing() {
		List<UserStats> userStatsList = new ArrayList<UserStats>();
		when(em.createNamedQuery("findUserStats", UserStats.class)).thenReturn(query);
		when(query.getResultList()).thenThrow(new NoResultException("No stats found for anybody!"));
		List<UserStats> returned = userStatsDAO.loadStatistics("golf", "12345");
		verify(logger).error(any(String.class), any(NoResultException.class));
		assertNull(returned);
	}
}
