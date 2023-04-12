package org.example.maus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * Click `Run` to execute the snippet below!
 * This was a shit interview.
 *
 * Sorry Siya
 *
 * That coding interview sucked for me.
 * It was a total disaster.
 * I had no idea how to implement the permutation algorithm that Michael Boyd was looking for.
 * It has been a very long time since I wrote basic computer algorithms back in university as a graduate student.
 *
 * :(
 */




/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

 /*
# First a bit of background, bp does a lot of work with fleets of vehicles.
# The scenario is that we are moving into e-scooters

# Each scooter in our customers e-scooter fleet needs to have a unique code assigned,
# the regulators will determine that codes should be generated according to a scheme
# that varies by city and region.

# Fortunately the schemes are similar enough that the regulators can define them
# by providing character sequences to pick from, e.g.

paris_params = [
    "*#&",
    "2345678",
]

london_params = [
    "abcdefghjklmnpqvwxy",
    "abcdefghjklmnpqvwxy",
    "-/",
    "0123"
]

# So for london example,  we would see the output
# aa-0, aa-1, aa-2, aa-3,
  aa/0, aa/1, aa/2, aa/3,
  ab-0, ab-3 and so on

  *2, *3, ..., *8, #2, ..., #8, ..., &8
  yy/3

  # Ask for code:
  #  Write code to accept these kind of inputs and print the first
  #  n scooter codes to standard output, starting with an entry point like below.
  def scootercode(params, n):

  scootercode(paris_params, 3)
  *2
  *3
  *4


   for j in param1
      for k in param2
         println( param1.charAt(j) + param2.charAt(k) )
  */


public class BPPermutationScooterCodesTest {

    static void _scooterCode( List<String> parameters, String prefix, int index, AtomicInteger counter, int limit) {
        if ( counter.get() > limit ) {
            return;
        }
        if ( index >= parameters.size()) {
            counter.incrementAndGet();
            System.out.println(prefix);
        }
        else {
            var length = parameters.get(index).length();
            for (int j=0; j<length; ++j) {
                _scooterCode(parameters, prefix+parameters.get(index).charAt(j),  index+1, counter, limit);
            }
        }
    }


    static void scooterCode( List<String> parameters, int limit) {

        _scooterCode( parameters, "",0, new AtomicInteger(0), limit);

        System.out.println();
    }

    @DisplayName("demonstrate permutation algorithm of scooter code for interview")
    @Test
    public void execute_solution() {
        var paris_params = List.of(
                "*#&",
                "2345678"
        );

        scooterCode(paris_params,16);

        var london_params = List.of(
                "abcdefghjklmnpqvwxy",
                "abcdefghjklmnpqvwxy",
                "-/",
                "0123" );

        scooterCode(london_params, 17);
    }
}
