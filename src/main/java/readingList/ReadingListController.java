package readingList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/readingList")
public class ReadingListController {

    @Autowired
    private ReadingListRepository readingListRepository;

    @GetMapping(value = "/{reader}")
    public String readerBooks(@PathVariable("reader") String reader, Model model) {

        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";

    }

    @PostMapping(value = "/{reader}")
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {

        book.setReader(reader);
        readingListRepository.save(book);

        return "redirect:/readingList/{reader}";
    }

}
