
###############################################
# Interface that every resource will implement
###############################################
class Resource:
    def get_title(self): pass
    def get_short_desc(self): pass
    def get_long_desc(self): pass
    def get_author(self): pass

##########################
# Concrete Resources
###########################
class BookResource(Resource):
    def get_title(self):
        return "Book Title"
    def get_short_desc(self):
        return "This is a Book"
    def get_long_desc(self):
        return "This is a much longer description for a book. The book is created by AdmiJW in year 2021 and it is " \
               "award winning"
    def get_author(self):
        return "AdmiJW"


class MovieResource(Resource):
    def get_title(self):
        return "Movie Title"
    def get_short_desc(self):
        return "This is a Movie"
    def get_long_desc(self):
        return "This is a much longer description for a movie. The movie is created by AdmiJW in year 2021 and it is " \
               "award winning"
    def get_author(self):
        return "AdmiJW"



#################
# Abstract View
#################
class View:
    def display(self): pass

################
# Concrete Views
################
class SimpleView(View):
    def __init__(self, resources):
        self._resources = [*resources]
    def add_resources(self, resources):
        self._resources.extend(resources)
    def display(self):
        for resource in self._resources:
            print( resource.get_title() )
            print( resource.get_short_desc(), end='\n\n' )


class DetailedView(View):
    def __init__(self, resources):
        self._resources = [*resources]
    def add_resources(self, resources):
        self._resources.extend(resources)
    def display(self):
        for resource in self._resources:
            print( resource.get_title() )
            print(resource.get_author() )
            print( resource.get_long_desc(), end='\n\n' )



if __name__ == '__main__':
    book = BookResource()
    movie = MovieResource()

    simplebookview = SimpleView( (book,) )
    detailedbookview = DetailedView( (book,) )
    simplemovieview = SimpleView( (movie, ) )
    detailedmovieview = DetailedView( (movie, ) )

    simplebookview.display()
    detailedbookview.display()
    simplemovieview.display()
    detailedmovieview.display()