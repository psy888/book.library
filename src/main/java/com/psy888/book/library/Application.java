package com.psy888.book.library;

import com.psy888.book.library.model.AuthorEntity;
import com.psy888.book.library.model.BookEntity;
import com.psy888.book.library.model.GenreEnum;
import com.psy888.book.library.service.BookLibraryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class Application {

	@Autowired
	private BookLibraryService bookLibraryService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public CommandLineRunner run (){
		return args -> {
			addTestRoman();
			addTestFantasy();
			printAuthorBooks();
			printFilteredBooks();
		};
	}


	public void addTestRoman(){
		BookEntity newRoman = new BookEntity();
		newRoman.setBooking("Подвал на улице Ленина");
		newRoman.setName("Армено и Желетта");
		newRoman.setPagesCount(300);
		newRoman.setReleaseDate(Year.of(2017));
		newRoman.setGenre(GenreEnum.ROMAN);

		AuthorEntity author = new AuthorEntity();
		author.setFirstName("Инокентий");
		author.setSecondName("Педросович");
		author.setLastName("Пупырышкин");
		author.setBirthDate(LocalDate.of(1990,4,12));
		newRoman.setAuthor(author);
		bookLibraryService.addNewBook(newRoman);
	}

	public void addTestFantasy(){
		BookEntity newFantasy = new BookEntity();
		newFantasy.setBooking("Крыша на улице Чекистов");
		newFantasy.setName("Пластелин Колец");
		newFantasy.setPagesCount(800);
		newFantasy.setReleaseDate(Year.of(2010));
		newFantasy.setGenre(GenreEnum.FANTASY);

		AuthorEntity author = bookLibraryService.findAuthorByFullName(
				"Инокентий",
				"Педросович",
				"Пупырышкин"
		);
		newFantasy.setAuthor(author);
		bookLibraryService.addNewBook(newFantasy);
	}

	public void printAuthorBooks(){
		List<BookEntity> books = bookLibraryService.findBooksByAuthor(
				"Инокентий",
				"Педросович",
				"Пупырышкин"
		);
		books.forEach(book -> log.info(book.toString()));
	}

	public void printFilteredBooks(){
		Set<GenreEnum> genres = new HashSet<>();
		genres.add(GenreEnum.FANTASY);
		genres.add(GenreEnum.ROMAN);

		List<BookEntity> books = bookLibraryService.findBooksByGenre(genres);
		books.forEach(book->log.info(book.toString()));
	}
}
