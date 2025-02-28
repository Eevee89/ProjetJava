package org.example.Services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.example.Entities.Appointment;
import org.example.Entities.WorkTime;
import org.example.Entities.Staff;
import org.example.Exceptions.AppointmentNotFoundException;
import org.example.Repositories.AppointmentRepository;
import org.example.Repositories.StaffRepository;
import org.example.Repositories.WorkTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private WorkTimeRepository WTrepository;

    @Autowired
    private StaffRepository staffRepository;

    public int isBusy(int centerId, Date dateToCheck) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToCheck);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int minH = 8;
        int maxH = 13;
        if ( 13 <= h ) {
            minH = 13;
            maxH = 18;
        }

        int dow = calendar.get(Calendar.DAY_OF_WEEK);
        String[] days = new String[8];
        days[Calendar.SUNDAY] = "Dimanche";
        days[Calendar.MONDAY] = "Lundi";
        days[Calendar.TUESDAY] = "Mardi";
        days[Calendar.WEDNESDAY] = "Mercredi";
        days[Calendar.THURSDAY] = "Jeudi";
        days[Calendar.FRIDAY] = "Vendredi";
        days[Calendar.SATURDAY] = "Samedi";
        String day = days[dow];

        int wt = WTrepository.findByDayAndHour(day, minH, maxH);
        int[] c = new int[1];
        c[0] = centerId;
        List<Staff> fromCenter = staffRepository.findByCenter(c);
        List<Staff> working = staffRepository.findByFreeAt(wt);

        List<Staff> doctors = new ArrayList<>(fromCenter);
        doctors.retainAll(working);

        List<Appointment> appointments = repository.findByCenterDoctorAndDate(centerId, calendar.getTime());
        List<Integer> doctorsIds = appointments.stream().map(app -> app.getDoctorId().getId()).toList();

        for (Staff doctor : doctors) {
            if (!doctorsIds.contains(doctor.getId())) {
                return doctor.getId();
            }
        }
        
        return -1;
    }

    public Appointment findOne(Integer id) throws AppointmentNotFoundException{
        return repository.findById(id)
            .orElseThrow(AppointmentNotFoundException::new);
    }

    public void create(Appointment a) {
        repository.save(a);
    }

    public List<Appointment> findAll() {
        return repository.findAll();
    }
}
