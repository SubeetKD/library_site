from django.db import models
from django.urls import reverse
import uuid  # Required for generation unique book instances


# Genre
class Genre(models.Model):
    """ Model representing a book """
    name = models.CharField(
        max_length=200, help_text="Enter a book genre(e.g. Politics)")

    def __str__(self):
        # string for representing model object
        return self.name

# Language Model


class Language(models.Model):
    language = models.CharField(
        max_length=50, help_text='Enter the language of the book.')

    def __str__(self):
        return self.language

# Book model


class Book(models.Model):
    # Model representing a book ( not a specific copy of book )
    title = models.CharField(max_length=200)

    # Foreign key is used because book can have only one author but an author can have multiple book
    author = models.ForeignKey('Author', on_delete=models.SET_NULL, null=True)

    summary = models.TextField(
        max_length=1000, help_text="Enter a brief description here.")
    isbn = models.CharField('ISBN', max_length=13)

    # Many to Many field
    genre = models.ManyToManyField(
        Genre, help_text='Select a genre for this book.')

    # Language of the book
    language = models.ForeignKey(
        Language, on_delete=models.SET_NULL, null=True)

    def __str__(self):
        return self.title

    def get_absolute_url(self):
        # Returns the url to access a detailed record for this book
        return reverse('book-detail', args=[str(self.id)])

# BookInstance Model


class BookInstance(models.Model):
    # Model representing a specific instance of book
    id = models.UUIDField(primary_key=True, default=uuid.uuid4,
                          help_text='Unique ID for this particular book.')
    book = models.ForeignKey(Book, on_delete=models.SET_NULL, null=True)
    imprint = models.CharField(max_length=200)
    due_back = models.DateField(null=True, blank=True)

    LOAN_STATUS = (
        ('m', 'Maintenance'),
        ('o', 'On Loan'),
        ('a', 'Available'),
        ('r', 'Reserved'),
    )

    status = models.CharField(max_length=1, choices=LOAN_STATUS,
                              blank=True, default='m', help_text='Book Availability')

    class Meta:
        ordering = ['due_back']

    def __str__(self):
        return f'{self.id} ({self.book.title})'

# Author Model


class Author(models.Model):
    first_name = models.CharField(max_length=100)
    last_name = models.CharField(max_length=100)
    date_of_birth = models.DateField(null=True, blank=True)
    date_of_death = models.DateField('Died', null=True, blank=True)

    class Meta:
        ordering = ['first_name', 'last_name']

    def get_absolute_url(self):
        return reverse('author-detail', args=[str(self.id)])

    def __str__(self):
        return f'{self.last_name} , {self.first_name}'
