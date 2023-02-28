################
# Post Interface
################
class PostInterface:
    def get_html(self): pass


#####################
# Post Object
#####################
class Post(PostInterface):
    def __init__(self, author, text):
        self._author = author
        self._text = text

    def get_html(self):
        return f"<p class='lead text-lg'>{self._author} : {self._text}</p>"

####################
# Null Object
####################
class EmptyPost(PostInterface):
    def get_html(self):
        return f"<p class='lead text-sm text-gray'>Anonymous doesn't seem to post anything...</p>"


if __name__ == '__main__':
    # Instead of None, use EmptyPost initially
    post = EmptyPost()

    while True:
        author = input("Enter author: ")
        msg = input("Enter post: ")

        if msg == "" and author == '':
            post = EmptyPost()
        else:
            post = Post(author, msg)

        print( post.get_html() )
