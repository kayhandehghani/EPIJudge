package epi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTest;

public class TaskPairing {
	@EpiUserType(ctorParams = { Integer.class, Integer.class })

	public static class PairedTasks {
		public Integer task1;
		public Integer task2;

		public PairedTasks(Integer task1, Integer task2) {
			this.task1 = task1;
			this.task2 = task2;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			PairedTasks that = (PairedTasks) o;

			return task1.equals(that.task1) && task2.equals(that.task2);
		}

		@Override
		public String toString() {
			return "[" + task1 + ", " + task2 + "]";
		}
	}

	@EpiTest(testDataFile = "task_pairing.tsv")

	public static List<PairedTasks> optimumTaskAssignment(List<Integer> taskDurations) {
		if (taskDurations == null) {
			return null;
		}

		List<PairedTasks> result = new ArrayList<>();
		Collections.sort(taskDurations);

		for (int i = 0; i < taskDurations.size(); i++) {
			int l = i, r = taskDurations.size() - l - 1;

			if (l == r) {
				result.add(new PairedTasks(taskDurations.get(l), null));
			} else if (l < r) {
				result.add(new PairedTasks(taskDurations.get(l), taskDurations.get(r)));
			}
		}

		return result;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "TaskPairing.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
