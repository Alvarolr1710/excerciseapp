package readingList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import readingList.amazon.AmazonProperties;
import readingList.repository.ReadingListRepository;
import readingList.utils.Book;
import readingList.utils.Reader;

import java.util.List;

@Controller
@RequestMapping("/")
@ConfigurationProperties("amazon")
public class ReadingListController {


    @Autowired
    private ReadingListRepository readingListRepository;
    @Autowired
    private AmazonProperties amazonProperties;

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
        return "redirect:/";
    }

}

