from django.contrib import admin
from .models import Author, Genre, Book, BookInstance

# Todo Not Completed


@admin.register(Author)
class AuthorAdmin(Author.ModelAdmin):
    pass


@admin.register(Genre)
class GenreAdmin(Genre.ModelAdmin):
    pass


@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
    pass


@admin.register(BookInstance)
class BookInstanceAdmin(admin.ModelAdmin):
    pass
