package PoliciesAndAlgorithms;

import jdk.nashorn.internal.parser.JSONParser;
//import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class RegularScorePolicy extends ScoreTablePolicy {
    int pointsForWinner = 3;
    int pointForLoser = 0;
    int pointsForDraw = 1;

    public int getPolicyPoints(){
        //fucntion for winner, loser, and sraw points.


        return 0;
    }
}






