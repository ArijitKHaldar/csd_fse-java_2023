package com.arijit.idp.helpermodules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserIdGeneratorTest {

	@Mock
	private Clock clock;

	@InjectMocks
	private UserIdGenerator userIdGenerator;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGenerateUserId() {
		String emailId = "test@example.com";
		String expectedUserId = "t2e0s2t200504205513";

		// Set the clock to a fixed time
		LocalDateTime fixedTime = LocalDateTime.of(2022, 5, 4, 15, 30, 22);
		Instant fixedInstant = fixedTime.atZone(ZoneId.systemDefault()).toInstant();
		when(clock.instant()).thenReturn(fixedInstant);
		when(clock.getZone()).thenReturn(ZoneId.systemDefault());

		String actualUserId = userIdGenerator.generateUserId(emailId);
		assertEquals(expectedUserId.substring(0, 7), actualUserId.substring(0, 7));
	}
}
