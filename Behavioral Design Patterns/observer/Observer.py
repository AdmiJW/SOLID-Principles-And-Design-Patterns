
############################################
# Generic Observer and Observable Interface
############################################
class Observable:
    def add_observer(self, observer): pass
    def remove_observer(self, observer): pass
    def notify(self): pass

class Observer:
    def update(self): pass


##################################
# Concrete Observer and Observable
##################################
class WeatherStation(Observable):
    def __init__(self, init_temp):
        self._temp = init_temp
        self._subscribers = set()

    def add_observer(self, observer):
        self._subscribers.add(observer)

    def remove_observer(self, observer):
        self._subscribers.discard(observer)

    def notify(self):
        for subscriber in self._subscribers:
            subscriber.update(self._temp)

    def get_weather(self):
        return self._temp

    def update_weather(self, new_temp):
        self._temp = new_temp
        self.notify()


class Phone(Observer):
    def __init__(self, weather_station):
        self._weather_station = weather_station
        self._weather_station.add_observer(self)
        self._temp = weather_station.get_weather()

    def update(self, new_temp):
        self._temp = new_temp
        print(f"Updated temperature to {self._temp}")



if __name__ == '__main__':
    weather_station = WeatherStation(37.5)
    phone1 = Phone(weather_station)
    phone2 = Phone(weather_station)

    weather_station.update_weather(38)
