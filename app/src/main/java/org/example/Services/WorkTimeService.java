package org.example.Services;

import org.example.Entities.WorkTime;
import org.example.Exceptions.PatientNotFoundException;
import org.example.Repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkTimeService {
    public final static int LUNDI = 1;
    public final static int MARDI = 2;
    public final static int MERCREDI = 3;
    public final static int JEUDI = 4;
    public final static int VENDREDI = 5;
    public final static int SAMEDI = 6;
    public final static int DIMANCHE = 7;
    public final static int MATIN = 0;
    public final static int APRESMIDI = 7;

    public static int getId(String day, boolean morning) {
        int res = morning ? MATIN : APRESMIDI;
        if (day.equals("lundi")) { return res+LUNDI; }
        else if (day.equals("mardi")) { return res+MARDI; }
        else if (day.equals("mercredi")) { return res+MERCREDI; }
        else if (day.equals("jeudi")) { return res+JEUDI; }
        else if (day.equals("vendredi")) { return res+VENDREDI;}
        else if (day.equals("samedi")) { return res+SAMEDI;}
        else { return res+DIMANCHE; }
    }
}
