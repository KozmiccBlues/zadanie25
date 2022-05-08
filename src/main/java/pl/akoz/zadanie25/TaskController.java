package pl.akoz.zadanie25;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "home";

    }

    @GetMapping("/all")
    public String all(Model model) {
        List<Task> all = taskRepository.findAllByOrderByDateAsc();
        model.addAttribute("tasks", all);
        return "all";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Task> add = taskRepository.findAll();
        model.addAttribute("taskToAdd", new Task());
        return "add";
    }

    @PostMapping("/add")
    public String addTask(Task task) {
        taskRepository.save(task);

        return "redirect:/all";
    }

    @GetMapping("/edit")
        public String showTaskToEdit(@PathVariable Long id, Model model) {
        Optional<Task> taskOptional = taskRepository.findById(id);

        Task task;
        if (taskOptional.isPresent()) {
            task = taskOptional.get();
            model.addAttribute("taskToEdit", task);

            return "edit";
        } else {
            return "redirect:/all";

        }
    }

    @PostMapping("/edit")
    public String editTask(@PathVariable Long id, Task task) {
        Task task1 = taskRepository.findById(id).orElseThrow();

        task1.setName(task.getName());
        task1.setDescription(task.getDescription());
        task1.setCategory(task.getCategory());
        task1.setDate(task.getDate());

        taskRepository.save(task1);

        return "redirect:/all/" + task.getId();
    }

    @GetMapping("/archive")
    public String archive(Model model) {
        List<Task> archive = taskRepository.findAll();
        model.addAttribute("archive", new Task());
        return "archive";
    }
}




