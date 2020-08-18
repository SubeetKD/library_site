from django.contrib import admin
from .models import Author, Genre, Book, BookInstance

# Todo Not Completed

# Define admin class
class AuthorAdmin(admin.ModelAdmin):
    """description"""
    pass
admin.site.register(Author,AuthorAdmin)

admin.site.register(Genre)
@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
    pass

@admin.register(BookInstance)
class BookInstanceAdmin(admin.ModelAdmin):
    pass
