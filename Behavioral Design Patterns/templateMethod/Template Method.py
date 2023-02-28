
##################################################
# Abstract Interface with Template method save()
##################################################
class Model:
    def save(self):
        # Ensure connection with database
        print("Database is connected. OK")
        # Ensure enough space in database
        print("Database has adequate space. OK")
        # The method that will be overridden.
        self._saveToDB()
        # Confirm that the data is saved
        print("Data is saved. OK")

    def _saveToDB(self): pass


##########################################
# Concrete classes that implement Model
###########################################
class User(Model):
    def _saveToDB(self):
        print("Saved User AdmiJW, ID: 12345")


class Comment(Model):
    def _saveToDB(self):
        print("Saved comment. ID: 12345, comment: 'Hi everyone'")


if __name__ == '__main__':
    user = User()
    comment = Comment()

    user.save()
    comment.save()
