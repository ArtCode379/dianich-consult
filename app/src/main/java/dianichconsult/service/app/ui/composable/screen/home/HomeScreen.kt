package dianichconsult.service.app.ui.composable.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dianichconsult.service.app.R
import dianichconsult.service.app.data.model.ServiceModel
import dianichconsult.service.app.ui.composable.shared.ANCSLContentWrapper
import dianichconsult.service.app.ui.composable.shared.ANCSLEmptyView
import dianichconsult.service.app.ui.state.DataUiState
import dianichconsult.service.app.ui.viewmodel.ServiceViewModel
import org.koin.androidx.compose.koinViewModel

private data class HeroSlide(val titleRes: Int, val subtitleRes: Int, val imageRes: Int)

private val heroSlides = listOf(
    HeroSlide(R.string.home_hero_title_1, R.string.home_hero_subtitle_1, R.drawable.service_strategy),
    HeroSlide(R.string.home_hero_title_2, R.string.home_hero_subtitle_2, R.drawable.service_cyber),
    HeroSlide(R.string.home_hero_title_3, R.string.home_hero_subtitle_3, R.drawable.service_cloud),
)

private data class CaseStudy(val titleRes: Int, val descRes: Int, val imageRes: Int)

private val caseStudies = listOf(
    CaseStudy(R.string.portfolio_1_title, R.string.portfolio_1_desc, R.drawable.portfolio1),
    CaseStudy(R.string.portfolio_2_title, R.string.portfolio_2_desc, R.drawable.portfolio2),
    CaseStudy(R.string.portfolio_3_title, R.string.portfolio_3_desc, R.drawable.portfolio3),
)

private data class KBArticle(val titleRes: Int, val descRes: Int, val imageRes: Int)

private val kbArticles = listOf(
    KBArticle(R.string.kb_1_title, R.string.kb_1_desc, R.drawable.article1),
    KBArticle(R.string.kb_2_title, R.string.kb_2_desc, R.drawable.article2),
    KBArticle(R.string.kb_3_title, R.string.kb_3_desc, R.drawable.article3),
)

private val categories = listOf("All", "Security", "Cloud", "Optimization", "Strategy", "Analytics", "Development")

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ServiceViewModel = koinViewModel(),
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val servicesState by viewModel.servicesState.collectAsState()

    HomeContent(
        servicesState = servicesState,
        modifier = modifier,
        onNavigateToServiceDetails = onNavigateToServiceDetails
    )
}

@Composable
private fun HomeContent(
    servicesState: DataUiState<List<ServiceModel>>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    Column(modifier = modifier) {
        ANCSLContentWrapper(
            dataState = servicesState,

            dataPopulated = {
                ServicesPopulated(
                    services = (servicesState as DataUiState.Populated).data,
                    onNavigateToServiceDetails = onNavigateToServiceDetails,
                )
            },

            dataEmpty = {
                ANCSLEmptyView(
                    primaryText = stringResource(R.string.services_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun ServicesPopulated(
    services: List<ServiceModel>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    var selectedCategory by remember { mutableStateOf("All") }
    val filteredServices = if (selectedCategory == "All") services
    else services.filter { it.category == selectedCategory }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp),
    ) {
        item {
            HeroCarousel()
        }

        item {
            SectionHeader(stringResource(R.string.home_section_services))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                categories.forEach { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category },
                        label = {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.surface,
                            labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                        border = FilterChipDefaults.filterChipBorder(
                            borderColor = Color(0xFF2A3140),
                            enabled = true,
                            selected = false,
                        ),
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        val rows = filteredServices.chunked(2)
        items(rows, key = { it.first().id }) { rowServices ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                rowServices.forEach { service ->
                    ServiceCard(
                        service = service,
                        modifier = Modifier.weight(1f),
                        onClick = { onNavigateToServiceDetails(service.id) }
                    )
                }
                if (rowServices.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            SectionHeader(stringResource(R.string.home_section_portfolio))
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(caseStudies) { study ->
                    CaseStudyCard(study)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            SectionHeader(stringResource(R.string.home_section_knowledge))
        }

        items(kbArticles) { article ->
            KBArticleCard(article)
        }
    }
}

@Composable
private fun HeroCarousel() {
    val pagerState = rememberPagerState(pageCount = { heroSlides.size })

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        ) { page ->
            val slide = heroSlides[page]
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = painterResource(id = slide.imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color(0xCC0F1419),
                                )
                            )
                        ),
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(20.dp),
                ) {
                    Text(
                        text = stringResource(slide.titleRes),
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = stringResource(slide.subtitleRes),
                        color = Color(0xFF00BFA6),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(heroSlides.size) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .height(6.dp)
                        .width(if (isSelected) 20.dp else 6.dp)
                        .clip(CircleShape)
                        .background(
                            if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.outline
                        )
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
    )
}

@Composable
private fun ServiceCard(
    service: ServiceModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF2A3140)),
    ) {
        Column {
            Image(
                painter = painterResource(id = service.imageRes),
                contentDescription = service.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                contentScale = ContentScale.Crop,
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = service.category.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = service.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "From £${String.format("%.0f", service.price)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun CaseStudyCard(study: CaseStudy) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF2A3140)),
        modifier = Modifier.width(280.dp),
    ) {
        Column {
            Image(
                painter = painterResource(id = study.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop,
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = stringResource(study.titleRes),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(study.descRes),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun KBArticleCard(article: KBArticle) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF2A3140)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Image(
                painter = painterResource(id = article.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(article.titleRes),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(article.descRes),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
