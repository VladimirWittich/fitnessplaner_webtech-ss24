# fitnessplaner_webtech-ss24
Projektidee:
Ein Fitnessplaner, der während des Trainings genutzt werden kann, weshalb sich auf die mobile Darstellung konzentriert wurde.
In den Fitnessplaner können sich mehr als eine Person einloggen.
Der Nutzer kann seine Größe, Gewicht und Geschlecht hinterlegen, woraus auch das BMI berechnet wird. 
Es können Übungen und zusätzlich die jeweiligen Sätze und Wiederholungen hinzugefügt werden.
Zudem wird das bewegte Gewicht pro Übung errechnet.
Diese Übungen werden mit dem jeweiligen Datum in der Datenbank gespeichert und können von dem Nutzer eingesehen werden.
In der Übersicht dieser hinterlegten Übungen kann auch nach Übungen gesucht werden.

Angedacht waren neben OKTA zwei zusätzliche APIs, die aufgrund fehlender Zeit nicht umgesetzt werden konnten:
1. Kalorienzähler mittels Smartwatch - Die Kalorien werden an den Fitnessplaner gesendet und mit den ausgeführten Übungen verrechnet.
2. Abspielen von Spotify-Favoritensongs - Zu verschiedenen Trainingskategorien können unterschiedliche Songs angelegt werden. Etwa zum Laufen kann eine andere Playlist angelegt werden als zum Kraftsport.
Optional wird Google Tensorflow Pose Estimation implementiert.

Entity-Klassen:
Personendaten: Hinterlegung der Größe, des Gewichtes und des Geschlechtes für das Errechnen des BMI.
Übunsplandaten: Übungen, Sätze und Wiederholungen zur Übung, woraus das bewegte Gewicht pro Übung errechnet wird.
