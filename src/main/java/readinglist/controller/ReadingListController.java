package readinglist.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import readinglist.amazon.AmazonProperties;
import readinglist.repository.ReadingListRepository;
import readinglist.utils.Book;
import readinglist.utils.Reader;

import java.util.List;

@Controller
@RequestMapping("/")
@ConfigurationProperties("amazon")
public class ReadingListController {


    @Autowired
    private ReadingListRepository readingListRepository;
    @Autowired
    private AmazonProperties amazonProperties;
    @Autowired
    private MeterRegistry meterRegistry;

    @RequestMapping(method = RequestMethod.GET)
    public String readersBooks(Reader reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonId", amazonProperties.getAssociateId());
        }
        return "readingList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToReadingList(Reader reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        meterRegistry.counter("books.saved").increment();
        return "redirect:/";
    }

}

