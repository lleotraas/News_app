# News_app

Il m'a fallu environ 10h pour réaliser cette application

J'utilise le plugin "Kotlin data class file from JSON" pour créer toutes les data class dont j'ai besoin afin de lire le fichier json que m'envoie les Response<> de Retrofit.

J'ai préféré utiliser Retrofit plutôt que le Client libraries de News Api car si jamais nous voulons personnaliser la recherche de news, nous pouvons le faire en utilisant la même Interface avec Retrofit 
alors qu'avec le client de New Api il faut utiliser un client différent selon la recherche.

J'ai utilisé Glide pour pouvoir afficher les images plus facilement avec une url. Quand je n'utilise pas Glide, la seule manière que je connais est de télécharger l'image en créant une api avec retrofit, transformer l'image en Bitmap
et ensuite l'afficher.

J'ai réalisé l'application avec Jetpack Compose car les applications sont plus rapides à écrire avec, les recycler view sont beaucoup moins lourdes a insérer par exemple.

J'ai utilisé Dagger-Hilt pour l'injection de dépendance, c'est devenu une habitude de l'utiliser dans tous mes projets maintenant. Même s'il n'y en avait pas forcément besoin pour cette application qui possède peu d'activités
 et de fragments j'ai préféré l'utiliser par prévention au cas où l'application a besoin d'améliorations. Ca sera ça de moins à ajouter.

Pour la clean architecture c'est la même chose que pour Dagger-Hilt peu d'intéret ici, mais dans le cas d'une amélioration de l'application...

Pour le test de NewsRepository j'ai utilisé postman en créant un mock server qui renvoie un json si l'adresse est bonne ou null si l'adresse est mauvaise.
