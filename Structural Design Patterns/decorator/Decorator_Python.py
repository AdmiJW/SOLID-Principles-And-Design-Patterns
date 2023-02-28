from functools import wraps


# The decorator function is a function that takes in an original function,
# and returns a modified function
def make_blink(original_function):
    # Use this to make the decorator transparent in terms of its name and docstring
    @wraps(original_function)

    def decorator():
        return f"<blink>{original_function()}<blink>"

    return decorator


# The function we want to decorate. Use @ followed by the decorator function
@make_blink
def hello_world():
    return "Hello World"


if __name__ == '__main__':
    print(hello_world() )


