
#################################
# Abstract interface of Subject
#################################
class BookParserInterface:
    def __init__(self, url): pass
    def get_no_of_pages(self): pass
    def get_no_of_words(self): pass
    def get_text(self): pass

##############################
# Concrete Subject
##############################
class BookParserSubject(BookParserInterface):
    def __init__(self, url):
        # Assume we went our way to download the whole book, and parsed it
        self._text = "Lorem Ipsum Dolor Sit Amet"
        self._no_of_pages = 13
        self._no_of_words = 5

    def get_no_of_pages(self):
        return self._no_of_pages
    def get_no_of_words(self):
        return self._no_of_words
    def get_text(self):
        return self._text


##############################
# Proxy
##############################
class BookParserProxy(BookParserInterface):
    # Cache of url -> meta information
    cache = {}

    def __init__(self, url):
        self._url = url
        self._subject = None

    def cache_book(self):
        BookParserProxy.cache[ self._url ] = {
            "no_of_pages": self._subject.get_no_of_pages(),
            "no_of_words": self._subject.get_no_of_words()
        }

    def get_no_of_pages(self):
        # First, check if we ever instantiated?
        if self._subject is not None:
            print("Actual Subject access")
            return self._subject.get_no_of_pages()

        # No actual subject. Check Cache
        if self._url in BookParserProxy.cache:
            print("Cache hit")
            return BookParserProxy.cache[self._url]['no_of_pages']

        # No choice but to instantiate
        print("Instantiation")
        self._subject = BookParserSubject(self._url)
        self.cache_book()
        return self._subject.get_no_of_pages()

    def get_no_of_words(self):
        # First, check if we ever instantiated?
        if self._subject is not None:
            print("Actual Subject access")
            return self._subject.get_no_of_words()

        # No actual subject. Check Cache
        if self._url in BookParserProxy.cache:
            print("Cache hit")
            return BookParserProxy.cache[self._url]['no_of_words']

        # No choice but to instantiate
        print("Instantiation")
        self._subject = BookParserSubject(self._url)
        self.cache_book()
        return self._subject.get_no_of_words()

    def get_text(self):
        # First, check if we ever instantiated?
        if self._subject is not None:
            print("Actual Subject access")
            return self._subject.get_text()

        # No choice but to instantiate
        print("Instantiation")
        self._subject = BookParserSubject(self._url)
        self.cache_book()
        return self._subject.get_text()


if __name__ == '__main__':
    print("Is it instantiated?")
    book = BookParserProxy('https://www.book.com/alienInvader.txt')
    print("Access word count")
    book.get_no_of_words()

    print("\n\nAnother instance")
    book2 = BookParserProxy('https://www.book.com/alienInvader.txt')
    print("Access page count")
    book2.get_no_of_pages()
    print("Access text")
    book2.get_text()
